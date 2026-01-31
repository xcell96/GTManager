package com.xcell.GTManager.model.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

/**
 * Service responsible for generating SQL queries using an external
 * AI provider.
 * <p>
 * This service builds a contextual prompt composed of a predefined
 * persona, a user-provided natural language prompt, and the current
 * database schema, then submits it to an AI API over HTTP.
 * <p>
 * Requests are executed asynchronously and return a {@link CompletableFuture}
 * containing the generated SQL statement or an error message.
 */
@Service
public class SqlAIService {

    /**
     * Base URL of the configured AI provider API.
     */
    @Value("${ai.provider.url}")
    private String apiUrl;

    /**
     * System-level persona used to guide AI behavior and output format.
     */
    @Value("${ai.bot.persona}")
    private String persona;

    /**
     * JSON object mapper used for request serialization and response parsing.
     */
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Generates a SQL query asynchronously based on a natural language prompt
     * and database schema context.
     *
     * @param prompt the user-provided natural language prompt
     * @param schema the formatted database schema description
     * @return a future containing the generated SQL query or an error message
     */
    @Async
    public CompletableFuture<String> generateAsync(String prompt, String schema) {
        try {
            String fullPrompt = persona + "\n\n" + prompt + "\n\nSchema:\n" + schema;
            System.out.println(fullPrompt);

            String payload = objectMapper.writeValueAsString(
                    new AiRequest("gemma3", fullPrompt, 1000000)
            );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(payload))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                return CompletableFuture.completedFuture(
                        "AI service returned HTTP " + response.statusCode() + ". Please try again."
                );
            }

            String sql = parseResponse(response.body());
            return CompletableFuture.completedFuture(sql);
        } catch (Exception ex) {
            return CompletableFuture.completedFuture("AI request failed. Please try again.");
        }
    }

    /**
     * Extracts the generated SQL query from the AI provider JSON response.
     *
     * @param json the raw JSON response body
     * @return the generated SQL query text, if present
     * @throws Exception if the response cannot be parsed
     */
    private String parseResponse(String json) throws Exception {
        JsonNode root = objectMapper.readTree(json);
        JsonNode choices = root.path("choices");
        if (choices.isArray() && !choices.isEmpty()) {
            String text = choices.get(0).path("text").asText("No reply");
            return text.replaceAll(";$", "").trim();
        }
        return "No reply";
    }

    /**
     * Request payload structure sent to the AI provider API.
     *
     * @param model the AI model identifier
     * @param prompt the full prompt provided to the AI model
     * @param max_tokens the maximum number of tokens allowed in the response
     */
    private record AiRequest(String model, String prompt, int max_tokens) {
    }
}
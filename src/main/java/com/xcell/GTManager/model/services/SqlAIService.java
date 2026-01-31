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

@Service
public class SqlAIService {

    @Value("${ai.provider.url}")
    private String apiUrl;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Async
    public CompletableFuture<String> generateAsync() {
        try {
            String payload = objectMapper.writeValueAsString(
                    new AiRequest("qwen2.5-coder:1.5b", "Give me a SQL query for all households.", 200)
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

    private String parseResponse(String json) throws Exception {
        JsonNode root = objectMapper.readTree(json);
        JsonNode choices = root.path("choices");
        if (choices.isArray() && !choices.isEmpty()) {
            String text = choices.get(0).path("text").asText("No reply");
            return text.replaceAll(";$", "").trim();
        }
        return "No reply";
    }

    private record AiRequest(String model, String prompt, int max_tokens) {
    }
}
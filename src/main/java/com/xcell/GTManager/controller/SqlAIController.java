package com.xcell.GTManager.controller;

import com.xcell.GTManager.model.services.SqlAIService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.CompletableFuture;

/**
 * REST controller that exposes an AI-assisted interface for generating
 * SQL queries based on natural language prompts.
 * <p>
 * The controller extracts database schema metadata at runtime and
 * provides it as contextual input to an AI service, which produces
 * SQL statements asynchronously.
 */
@RestController
@RequestMapping("/sqlquery/ai")
public class SqlAIController {

    /**
     * JDBC template used to retrieve database schema metadata.
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Service responsible for generating SQL queries using AI.
     */
    private final SqlAIService sqlAiService;

    /**
     * Creates a new {@code SqlAIController} with the required dependencies.
     *
     * @param jdbcTemplate the JDBC template used for database access
     * @param sqlAiService the AI service used to generate SQL queries
     */
    public SqlAIController(JdbcTemplate jdbcTemplate, SqlAIService sqlAiService) {
        this.jdbcTemplate = jdbcTemplate;
        this.sqlAiService = sqlAiService;
    }

    /**
     * Processes a chat-style request containing a natural language prompt
     * and returns an AI-generated SQL query.
     * <p>
     * The database schema is dynamically introspected and formatted before
     * being passed to the AI service as contextual information.
     *
     * @param body the request body containing the user prompt
     * @return a future containing the generated SQL query wrapped in a response map
     */
    @PostMapping("/chat")
    public CompletableFuture<Map<String, String>> chat(@RequestBody Map<String, String> body) {
        String message = body.get("message");
        if (message == null || message.isBlank()) {
            return CompletableFuture.completedFuture(Map.of("reply", "Please provide a prompt."));
        }

        List<Map<String, Object>> rows = jdbcTemplate.queryForList("""
            SELECT table_name, column_name, data_type
            FROM information_schema.columns
            WHERE table_schema = DATABASE()
            ORDER BY table_name, ordinal_position
        """);

        String schema = formatSchema(rows);

        return sqlAiService.generateAsync(message, schema)
                .thenApply(sql -> Map.of("reply", sql));
    }

    /**
     * Formats database schema metadata into a compact, human-readable form
     * suitable for use as contextual input to an AI model.
     *
     * @param rows the raw schema metadata retrieved from the database
     * @return a formatted schema description string
     */
    private String formatSchema(List<Map<String, Object>> rows) {
        Map<String, StringJoiner> tables = new LinkedHashMap<>();

        for (Map<String, Object> row : rows) {
            String table = String.valueOf(row.get("table_name"));
            String column = String.valueOf(row.get("column_name"));
            String type = String.valueOf(row.get("data_type"));

            tables.computeIfAbsent(table, t -> new StringJoiner(", ", t + "(", ")"))
                    .add(column + " " + type);
        }

        return String.join("\n", tables.values().stream().map(StringJoiner::toString).toList());
    }
}

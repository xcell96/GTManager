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

@RestController
@RequestMapping("/sqlquery/ai")
public class SqlAIController {

    private final JdbcTemplate jdbcTemplate;
    private final SqlAIService sqlAiService;

    public SqlAIController(JdbcTemplate jdbcTemplate, SqlAIService sqlAiService) {
        this.jdbcTemplate = jdbcTemplate;
        this.sqlAiService = sqlAiService;
    }

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

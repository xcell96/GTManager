package com.xcell.GTManager.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Controller for executing manual SQL queries.
 * Provides endpoints for displaying the SQL query page and executing queries.
 */
@Controller
@RequestMapping("/sqlquery")
public class SqlQueryController {

    private static final int MAX_ROWS = 500;

    private final JdbcTemplate jdbcTemplate;

    public SqlQueryController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public String sqlQueryPage() {
        return "sqlquery";
    }

    @PostMapping("/execute")
    public String executeSqlQuery(@RequestParam("query") String query, Model model) {
        model.addAttribute("query", query);

        if (query == null || query.isBlank()) {
            model.addAttribute("error", "Please enter a SQL query.");
            return "sqlquery";
        }

        String normalized = query.trim();
        if (normalized.endsWith(";")) {
            normalized = normalized.substring(0, normalized.length() - 1).trim();
        }

        if (!isSafeSelect(normalized)) {
            model.addAttribute("error", "Only single SELECT statements are allowed.");
            return "sqlquery";
        }

        try {
            jdbcTemplate.setMaxRows(MAX_ROWS);

            List<Map<String, Object>> results = jdbcTemplate.queryForList(normalized);

            model.addAttribute("results", results);
            model.addAttribute("rowCount", results.size());

            model.addAttribute("results", results);
            model.addAttribute("rowCount", results.size());

            if (!results.isEmpty()) {
                List<String> columnNames = new ArrayList<>(results.getFirst().keySet())
                        .stream()
                        .map(String::valueOf)
                        .toList();

                model.addAttribute("columnNames", columnNames);
            } else {
                model.addAttribute("message", "Query executed successfully. No rows returned.");
            }
        } catch (DataAccessException e) {
            model.addAttribute("error", "Query execution failed.");
        }

        return "sqlquery";
    }

    private boolean isSafeSelect(String sql) {
        String upper = sql.toUpperCase(Locale.ROOT);

        if (!(upper.startsWith("SELECT") || upper.startsWith("DESC"))) {
            return false;
        }

        if (upper.contains(";")) {
            return false;
        }

        return !upper.matches(".*\\b(INSERT|UPDATE|DELETE|DROP|ALTER|TRUNCATE|CREATE|MERGE|CALL|EXEC)\\b.*");
    }
}
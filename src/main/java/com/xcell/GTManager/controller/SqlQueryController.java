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
 * Spring MVC controller that exposes a web interface for executing
 * manually entered SQL queries against the configured data source.
 * <p>
 * This controller only allows execution of single, read-only SQL
 * statements (SELECT or DESC) and enforces a maximum row limit to
 * prevent excessive result sizes.
 */
@Controller
@RequestMapping("/sqlquery")
public class SqlQueryController {

    /**
     * Maximum number of rows that may be returned by a query execution.
     */
    private static final int MAX_ROWS = 500;

    /**
     * JDBC template used to execute SQL queries.
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Creates a new {@code SqlQueryController} with the given JDBC template.
     *
     * @param jdbcTemplate the JDBC template used for database access
     */
    public SqlQueryController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Displays the SQL query input page.
     *
     * @return the name of the SQL query view
     */
    @GetMapping
    public String sqlQueryPage() {
        return "sqlquery";
    }

    /**
     * Executes a user-provided SQL query after validation and safety checks.
     * <p>
     * Only single SELECT-like statements are permitted. Query results,
     * column names, and execution metadata are added to the model for
     * rendering in the view.
     *
     * @param query the SQL query entered by the user
     * @param model the model used to pass attributes to the view
     * @return the name of the SQL query view
     */
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

    /**
     * Determines whether the given SQL statement is a safe, read-only query.
     * <p>
     * A query is considered safe if it is a single SELECT or DESC statement
     * and does not contain disallowed keywords or multiple statements.
     *
     * @param sql the SQL statement to validate
     * @return {@code true} if the statement is considered safe; {@code false} otherwise
     */
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
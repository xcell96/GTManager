package com.xcell.GTManager.controller;

import com.xcell.GTManager.model.services.SqlAIService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/sqlquery/ai")
public class SqlAIController {

    private final SqlAIService sqlAiService;

    public SqlAIController(SqlAIService sqlAiService) {
        this.sqlAiService = sqlAiService;
    }

    @PostMapping("/chat")
    public CompletableFuture<Map<String, String>> chat() {
        return sqlAiService.generateAsync()
                .thenApply(sql -> Map.of("reply", sql));
    }
}

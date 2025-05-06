package com.example.pc_piatto.deepseek;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
@RequestMapping("/api/deepseek")
public class DeepSeekController {

    private final DeepSeekService deepSeekService;

    public DeepSeekController(DeepSeekService deepSeekService) {
        this.deepSeekService = deepSeekService;
    }

    @PostMapping("/chat")
    public ResponseEntity<Map<String, String>> chat(@RequestBody Map<String, String> request) {
        String prompt = request.get("prompt");
        if (prompt == null || prompt.isEmpty()) {
            throw new IllegalArgumentException("El prompt no puede estar vacío");
        }

        String response = deepSeekService.getChatResponse(prompt);
        return ResponseEntity.ok(Map.of(
                "response", response,
                "model", "deepseek/DeepSeek-R1",
                "timestamp", Instant.now().toString()
        ));
    }
}
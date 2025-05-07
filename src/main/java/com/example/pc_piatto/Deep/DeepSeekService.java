package com.example.pc_piatto.Deep;

import lombok.Value;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;

import java.util.*;
import java.util.logging.Logger;

@Service
public class DeepSeekService {

    private static final Logger logger = LoggerFactory.getLogger(DeepSeekService.class);

    private final RestTemplate restTemplate;
    private final String apiEndpoint;
    private final String modelName;

    public DeepSeekService(@Qualifier("deepSeekRestTemplate") RestTemplate restTemplate,
                           @Value("${github.deepseek.endpoint}") String apiEndpoint,
                           @Value("${github.deepseek.model}") String modelName) {
        this.restTemplate = restTemplate;
        this.apiEndpoint = apiEndpoint;
        this.modelName = modelName;
    }

    public String getChatResponse(String prompt) {
        String url = apiEndpoint + "/chat/completions";

        // Estructura de solicitud para DeepSeek
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", modelName);
        requestBody.put("messages", List.of(
                Map.of("role", "user", "content", prompt)
        ));
        requestBody.put("max_tokens", 1000);
        requestBody.put("temperature", 0.7);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(
                    url,
                    requestBody,
                    Map.class);

            // Procesar respuesta de DeepSeek
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("choices")) {
                List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
                if (!choices.isEmpty()) {
                    Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                    return (String) message.get("content");
                }
            }
            throw new RuntimeException("Respuesta inválida de DeepSeek");

        } catch (HttpClientErrorException e) {
            logger.error("Error al llamar a DeepSeek API: " + e.getResponseBodyAsString());
            throw new RuntimeException("Error en la API de DeepSeek: " + e.getMessage(), e);
        }
    }

    // Método para streaming (si la API lo soporta)
    public Flux<String> getStreamingResponse(String prompt) {
        // Implementación similar usando WebClient para streaming
        // ...
    }
}
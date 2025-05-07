package com.example.pc_piatto.Deep;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GitHubModelsConfig {

    // CORRECCIÓN 1: Usar @Value con el nombre de la propiedad, no el valor directo
    @Value("${github.deepseek.token}")
    private String apiToken;

    // CORRECCIÓN 2: Usar @Value con el nombre de la propiedad, no la URL directa
    @Value("${github.deepseek.endpoint}")
    private String apiEndpoint;

    @Bean
    public RestTemplate deepseekRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        // Configurar timeout
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000);
        requestFactory.setReadTimeout(30000);
        restTemplate.setRequestFactory(requestFactory);

        // Interceptor para autenticación
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + apiToken);
            request.getHeaders().add("Content-Type", "application/json");
            request.getHeaders().add("X-Model-Name", "deepseek/DeepSeek-R1");
            return execution.execute(request, body);
        });

        return restTemplate;
    }
}
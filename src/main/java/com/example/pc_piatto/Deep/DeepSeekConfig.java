package com.example.pc_piatto.Deep;

import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DeepSeekConfig {

    @Value("${github.deepseek.token}")
    private String githubToken;
    
    @Value("${github.deepseek.endpoint}")
    private String apiEndpoint;
    
    @Value("${github.deepseek.model}")
    private String modelName;
    
    @Bean
    public RestTemplate deepSeekRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        
        // Configurar timeout
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(5000); // 5 segundos
        requestFactory.setReadTimeout(30000); // 30 segundos
        
        restTemplate.setRequestFactory(requestFactory);
        
        // Configurar interceptor para el token
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + githubToken);
            request.getHeaders().add("Content-Type", "application/json");
            request.getHeaders().add("X-Model-Name", modelName);
            return execution.execute(request, body);
        });
        
        return restTemplate;
    }
}
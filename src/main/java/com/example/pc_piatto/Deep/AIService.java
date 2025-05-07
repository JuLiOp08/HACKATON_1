package com.example.pc_piatto.Deep;

import org.springframework.stereotype.Service;

@Service
public class AIService {
    
    private final GitHubModelsClient modelsClient;
    private final RequestRepository requestRepository;
    private final UserLimitService userLimitService;
    
    public AIService(GitHubModelsClient modelsClient, 
                    RequestRepository requestRepository,
                    UserLimitService userLimitService) {
        this.modelsClient = modelsClient;
        this.requestRepository = requestRepository;
        this.userLimitService = userLimitService;
    }
    
    public ChatResponse processChatRequest(ChatRequestDTO request, User user) {
        // 1. Verificar límites del usuario
        userLimitService.checkUserLimits(user, "CHAT_MODEL");
        
        // 2. Realizar la solicitud al modelo
        ModelRequest modelRequest = new ModelRequest.Builder()
            .withModel(request.getModel())
            .withPrompt(request.getPrompt())
            .withMaxTokens(request.getMaxTokens())
            .build();
            
        ModelResponse response = modelsClient.chat(modelRequest);
        
        // 3. Registrar la solicitud
        RequestEntity requestEntity = new RequestEntity();
        requestEntity.setUser(user);
        requestEntity.setCompany(user.getCompany());
        requestEntity.setModel(request.getModel());
        requestEntity.setQuery(request.getPrompt());
        requestEntity.setResponse(response.getText());
        requestEntity.setTokensUsed(response.getTokensUsed());
        requestEntity.setTimestamp(LocalDateTime.now());
        requestRepository.save(requestEntity);
        
        // 4. Actualizar contadores de límites
        userLimitService.updateUsage(user, "CHAT_MODEL", response.getTokensUsed());
        
        return new ChatResponse(response.getText(), response.getTokensUsed());
    }
    
    // Métodos similares para completion y multimodal
}
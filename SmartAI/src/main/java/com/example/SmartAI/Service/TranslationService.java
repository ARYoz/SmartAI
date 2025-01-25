package com.example.SmartAI.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import java.util.Map;

@Service
public class TranslationService {

    private static final String GPT_API_URL = "https://api.openai.com/v1/completions";
    private static final String API_KEY = "YOUR_API_KEY";

    public String translateWithGPT(String text) {
        RestTemplate restTemplate = new RestTemplate();




        // Request body
        Map<String, Object> requestBody = Map.of(
                "model", "text-davinci-003",
                "max_tokens", 100,
                "temperature", 0.7
        );

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + API_KEY);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(GPT_API_URL, entity, String.class);
        return response.getBody();
    }
}

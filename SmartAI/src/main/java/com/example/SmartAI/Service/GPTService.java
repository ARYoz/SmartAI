package com.example.SmartAI.Service;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.Map;

@Service
public class GPTService {

    private final RestTemplate restTemplate;
    private final String apiKey = "YOUR_OPENAI_API_KEY";

    public GPTService() {
        this.restTemplate = new RestTemplate();
    }

    public String askGPT(String prompt) {
        String url = "https://api.openai.com/v1/completions";


// יצירת HttpHeaders
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey); // הגדרת Authorization עם המפתח שלך

// יצירת גוף הבקשה
        Map<String, Object> body = new HashMap<>();
        body.put("model", "text-davinci-003"); // דגם GPT
        body.put("prompt", prompt); // הטקסט לשאילתא
        body.put("max_tokens", 100); // הגבלת טוקנים

// יצירת HttpEntity עם הכותרות והגוף
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

// שליחת הבקשה באמצעות RestTemplate
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

// הצגת התגובה
        System.out.println(response.getBody());


        return response.getBody();
    }
}

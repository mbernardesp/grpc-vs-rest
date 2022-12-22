package com.mbp.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class RestService {

    @Value("${rest.service.endpoint}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public Object getUnaryResponse(int number){

        Map<Integer, String> map = new HashMap<>();

        for (int i = 1; i <= number ; i++) {
            ResponseEntity<String> responseEntity = this.restTemplate.getForEntity(this.baseUrl + String.format("/rest/unary/%d", number), String.class);
            map.put(i, responseEntity.getBody());
        }
        return map;
    }

}

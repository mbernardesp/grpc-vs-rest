package com.mbp.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class RestService {

    @Value("${rest.service.endpoint}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public Object getUnaryResponse(int number){

        //Date start
        long ds = System.currentTimeMillis();

        Map<Integer, String> map = new HashMap<>();

        for (int i = 1; i <= number ; i++) {
            ResponseEntity<String> responseEntity = this.restTemplate.getForEntity(this.baseUrl + String.format("/rest/unary/%d", number), String.class);
            map.put(i, responseEntity.getBody());
        }

        //Date end
        long de = System.currentTimeMillis();
        System.out.println("Rest," + (de-ds));

        return map;
    }

    public Object getMultiplicationResponse(int number1, int number2){


        for (int i = 1; i < 100 ; i++) {

            //Date start
            long ds = System.currentTimeMillis();

            ResponseEntity<Integer> responseEntity = this.restTemplate.getForEntity(this.baseUrl + String.format("/rest/multiplication/%d/%d", number1, number2), Integer.class);

            //Date end
            long de = System.currentTimeMillis();
            System.out.println("Rest:" + (de-ds));

            try {
                Thread.sleep(5);
            } catch (Exception e){

            }
        }

        return "Ola";
    }

}

package com.example.productservice.client.orderServiceClient;

import com.example.productservice.client.orderServiceClient.dtos.OrderServiceCreateOrderRequestDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Component
public class OrderServiceClient {

    private String orderServiceBaseUrl = "http://localhost:8081/";
    private String orderServiceOrderPath = "order/";
    private RestClient restClient;
    private Map<String,Object> params;

    public OrderServiceClient() {
        String uri = UriComponentsBuilder.fromUriString(orderServiceBaseUrl).toUriString();
        this.restClient = RestClient.builder().baseUrl(uri).build();
        this.params = new HashMap<>();
    }

    public String createOrder(OrderServiceCreateOrderRequestDto requestDto){
        String uri = UriComponentsBuilder.fromUriString(orderServiceOrderPath).toUriString();

        return restClient.post()
                .uri(uri)
                .body(requestDto)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(String.class);
    }
}

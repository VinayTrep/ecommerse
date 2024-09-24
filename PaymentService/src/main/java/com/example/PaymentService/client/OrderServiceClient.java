package com.example.PaymentService.client;

import com.example.PaymentService.client.dtos.OrderServiceOrderResponseDto;
import com.example.PaymentService.exception.InvalidOrderIdException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Component
public class OrderServiceClient {

    private String orderServiceBaseUrl = "http://localhost:8081";
    private String orderServiceOrderPath = "/order/";
    private RestClient restClient;
    private final Map<String, Object> pathVariables;

    public OrderServiceClient() {
        String url = UriComponentsBuilder.fromUriString(orderServiceBaseUrl).toUriString();
        this.restClient = RestClient.builder()
                .baseUrl(url).build();
        this.pathVariables = new HashMap<>();
    }

    public OrderServiceOrderResponseDto getOrderIfExists(UUID orderId) {
        pathVariables.clear();
        pathVariables.put("orderId", orderId);
        String url = UriComponentsBuilder
                .fromUriString(orderServiceOrderPath + "{orderId}")
                .uriVariables(pathVariables)
                .toUriString();

        try{
            OrderServiceOrderResponseDto body = restClient.get()
                    .uri(url).retrieve()
                    .body(OrderServiceOrderResponseDto.class);
            return body;
        }catch (HttpClientErrorException e){
            throw new InvalidOrderIdException(e.getResponseBodyAsString());
        }
    }

}

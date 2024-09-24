package com.example.OrderService.client;

import com.example.OrderService.client.dto.paymentDto.GeneratePaymentLinkDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;
@Component
public class PaymentServiceClient {
    @Value("${paymentservice.base.url}")
    private String paymentServiceBaseUrl;
    @Value("${paymentservice.payment.url}")
    private String paymentServicePaymentUrl;

    private RestClient restClient;

    public PaymentServiceClient() {
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8082")
                .build();
    }

    public String generatePaymentUrl(GeneratePaymentLinkDto request){
        String url = UriComponentsBuilder
                .fromUriString(paymentServicePaymentUrl)
                .toUriString();
        System.out.println("===============");
        System.out.println(restClient);
        System.out.println(url);
        System.out.println("===============");
        return restClient.post()
                .uri(url)
                .body(request)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(String.class);
    }
}

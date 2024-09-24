package com.example.PaymentService.config;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorpayClientConfig {

    String razorpayApiKey = "rzp_test_9WHWDeroKbahPJ";

    String razorpayApiSecret = "G3XxMXAwRIrfpGiki2vDPhvO";

    @Bean
    public RazorpayClient razorpayClient() throws RazorpayException {
        return new RazorpayClient(razorpayApiKey, razorpayApiSecret);
    }
}

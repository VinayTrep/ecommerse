package com.example.PaymentService.paymentadapter.razorpay;

import com.example.PaymentService.config.RazorpayClientConfig;
import com.example.PaymentService.dto.OrderResponseDto;
import com.example.PaymentService.exception.CreatePaymentLinkException;
import com.example.PaymentService.paymentadapter.PaymentGatewayAdapter;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component("RazorpayPaymentGateway")
public class RazorpayPaymentGateway implements PaymentGatewayAdapter {


    private final RazorpayClientConfig razorpayClientConfig;

    public RazorpayPaymentGateway(RazorpayClientConfig razorpayClientConfig) {
        this.razorpayClientConfig = razorpayClientConfig;
    }



    @Override
    public String makePayment(OrderResponseDto orderResponseDto, UUID paymentId, long amount) {


        try {
            RazorpayClient razorpay = razorpayClientConfig.razorpayClient();
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount",amount);
            paymentLinkRequest.put("currency","INR");
            paymentLinkRequest.put("accept_partial",true);
            paymentLinkRequest.put("first_min_partial_amount",100);
            paymentLinkRequest.put("expire_by",Instant.now().toEpochMilli() + 10000);
            paymentLinkRequest.put("reference_id", UUID.randomUUID());
            paymentLinkRequest.put("description","Payment for policy no #23456");
            JSONObject customer = new JSONObject();
            customer.put("name","vinayLJ");
            customer.put("contact","+919535812944");
            customer.put("email","ljvinayofficial@gmail.com");
            paymentLinkRequest.put("customer",customer);
            JSONObject notify = new JSONObject();
            notify.put("sms",true);
            notify.put("email",true);
            paymentLinkRequest.put("notify",notify);
            paymentLinkRequest.put("reminder_enable",true);
            paymentLinkRequest.put("callback_url","https://www.youtube.com/");
            paymentLinkRequest.put("callback_method","get");

            PaymentLink payment = razorpay.paymentLink.create(paymentLinkRequest);
            return payment.get("short_url");
        } catch (RazorpayException e) {
            throw new CreatePaymentLinkException(e.getMessage());
        }
    }
}

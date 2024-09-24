package com.example.PaymentService.paymentadapter;

import com.example.PaymentService.config.RazorpayClientConfig;
import com.example.PaymentService.paymentadapter.razorpay.RazorpayPaymentGateway;
import com.example.PaymentService.paymentadapter.stripe.PaymentGatewaySupported;
import com.example.PaymentService.paymentadapter.stripe.StripePaymentGateway;


public class PaymentGatewayFactory {

    private static RazorpayClientConfig razorpayClientConfig = new RazorpayClientConfig();
    public static PaymentGatewayAdapter getPaymentGatewayAdapter(PaymentGatewaySupported supported){

       return switch (supported){
           case RAZORPAY -> new RazorpayPaymentGateway(razorpayClientConfig);
           case STRIPE -> new StripePaymentGateway();
       };
    }
}

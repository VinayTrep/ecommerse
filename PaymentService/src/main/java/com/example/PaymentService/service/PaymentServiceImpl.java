package com.example.PaymentService.service;

import com.example.PaymentService.client.OrderServiceClient;
import com.example.PaymentService.dto.CreatePaymentLinkRequestDto;
import com.example.PaymentService.dto.OrderResponseDto;
import com.example.PaymentService.model.Payment;
import com.example.PaymentService.model.PaymentStatus;
import com.example.PaymentService.paymentadapter.PaymentGatewayAdapter;
import com.example.PaymentService.paymentadapter.PaymentGatewayFactory;
import com.example.PaymentService.paymentadapter.stripe.PaymentGatewaySupported;
import com.example.PaymentService.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{
    private  PaymentGatewayAdapter paymentGatewayAdapter;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderServiceClient orderServiceClient;

    @Override
    public String generatePayment(CreatePaymentLinkRequestDto createPaymentLinkRequestDto){
        if(Math.random()*10 < 5) {
            this.paymentGatewayAdapter = PaymentGatewayFactory.getPaymentGatewayAdapter(PaymentGatewaySupported.RAZORPAY);
        }else{
            this.paymentGatewayAdapter = PaymentGatewayFactory.getPaymentGatewayAdapter(PaymentGatewaySupported.STRIPE);
        }

        //verify if orderId exist
        OrderResponseDto orderResponseDto = OrderResponseDto.from(orderServiceClient.getOrderIfExists(createPaymentLinkRequestDto.getOrderId()));

        Payment payment = new Payment();
        payment.setAmount(orderResponseDto.getAmount());
        payment.setPaymentStatus(PaymentStatus.IN_PROGRESS);
        payment.setOrderId(createPaymentLinkRequestDto.getOrderId());
        payment.setUserId(createPaymentLinkRequestDto.getUserId());

        Payment savedPayment = paymentRepository.save(payment);


        return paymentGatewayAdapter.makePayment(orderResponseDto,savedPayment.getPaymentReferenceId()
                ,orderResponseDto.getAmount());
    }
}

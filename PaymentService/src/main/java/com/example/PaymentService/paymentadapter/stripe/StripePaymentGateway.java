package com.example.PaymentService.paymentadapter.stripe;

import com.example.PaymentService.dto.OrderResponseDto;
import com.example.PaymentService.exception.CreatePaymentLinkException;
import com.example.PaymentService.paymentadapter.PaymentGatewayAdapter;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("StripePaymentGateway")
public class StripePaymentGateway implements PaymentGatewayAdapter {

    String stripeSecretKey = "sk_test_51Pco4GJq2sFRpIElW3fYYX6RIAWbdDTW7ZsqHsUe2cEmvcdZP8tmQFj2pQ0Cebm8eRBKubXP0RZOxgoMpjOihHUD00QNU5GP5D";
    @Override
    public String makePayment(OrderResponseDto orderResponseDto, UUID paymentId, long amount) {

        try{
            Stripe.apiKey = stripeSecretKey;
            ProductCreateParams createProduct =
                    ProductCreateParams.builder().setName(paymentId.toString()).build();
            Product product = Product.create(createProduct);

            // Set your secret key. Remember to switch to your live secret key in production.
// See your keys here: https://dashboard.stripe.com/apikeys
            Stripe.apiKey = "sk_test_51Pco4GJq2sFRpIElW3fYYX6RIAWbdDTW7ZsqHsUe2cEmvcdZP8tmQFj2pQ0Cebm8eRBKubXP0RZOxgoMpjOihHUD00QNU5GP5D";

            PriceCreateParams createPrice =
                    PriceCreateParams.builder()
                            .setCurrency("inr")
                            .setUnitAmount(amount)
                            .setProduct(product.getId())
                            .build();

            Price price = Price.create(createPrice);


            PaymentLinkCreateParams params =
                    PaymentLinkCreateParams.builder()
                            .addLineItem(
                                    PaymentLinkCreateParams.LineItem.builder()
                                            .setPrice(price.getId())
                                            .setQuantity(1L)
                                            .build()
                            )
                            .build();

            PaymentLink paymentLink = PaymentLink.create(params);
            return paymentLink.getUrl();
        } catch (StripeException e) {
            throw new CreatePaymentLinkException(e.getMessage());
        }
    }
}

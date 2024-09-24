package com.example.PaymentService.controller.webhook;

import com.stripe.model.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    @PostMapping("/stripe/")
    public void stripe(@RequestBody Event event) {
        System.out.println("this is webhook event");
        System.out.println(event);
        // Deserialize the nested object inside the event
        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        StripeObject stripeObject = null;
        if (dataObjectDeserializer.getObject().isPresent()) {
            stripeObject = dataObjectDeserializer.getObject().get();
        } else {
            // Deserialization failed, probably due to an API version mismatch.
            // Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
            // instructions on how to handle this case, or return an error here.
        }
        // Handle the event
        switch (event.getType()) {
            case "payment_intent.succeeded":
                PaymentIntent paymentIntentSuccess = (PaymentIntent) stripeObject;
                System.out.println("PaymentIntent was successful!" + paymentIntentSuccess);
                break;
            case "payment_intent.canceled":
                PaymentIntent paymentIntentCanceled = (PaymentIntent) stripeObject;
                System.out.println("PaymentMethod was attached to a Customer!" + paymentIntentCanceled);
                break;
            case "payment_intent.payment_failed":
                PaymentIntent paymentIntentFailed = (PaymentIntent) stripeObject;
                System.out.println("PaymentMethod was attached to a Customer!" + paymentIntentFailed);
                break;
            // ... handle other event types payment_intent.payment_failed
            default:
                System.out.println("Unhandled event type: " + event.getType());
        }
    }
}

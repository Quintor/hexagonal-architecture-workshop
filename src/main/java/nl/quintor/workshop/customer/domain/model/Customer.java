package nl.quintor.workshop.customer.domain.model;

import lombok.*;

import java.util.UUID;

@Value
@AllArgsConstructor
@Builder
public class Customer {
    UUID id;
    String name;
    String phoneNumber;

    @Builder.Default
    CustomerStatus status = CustomerStatus.ACTIVE;
}


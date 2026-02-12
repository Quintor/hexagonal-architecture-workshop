package nl.quintor.workshop.customer.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    UUID id;
    String email;
    String phoneNumber;

    @Builder.Default
    CustomerStatus status = CustomerStatus.ACTIVE;
}


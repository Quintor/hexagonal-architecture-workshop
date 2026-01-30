package nl.quintor.workshop.customer.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    long id;
    String phoneNumber;
    String email;
    CustomerStatus status = CustomerStatus.ACTIVE;
}


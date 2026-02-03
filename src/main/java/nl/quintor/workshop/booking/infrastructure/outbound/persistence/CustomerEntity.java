package nl.quintor.workshop.booking.infrastructure.outbound.persistence;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CustomerEntity {
    private String email;
    private String phoneNumber;
}

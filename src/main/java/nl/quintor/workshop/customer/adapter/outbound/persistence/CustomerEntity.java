package nl.quintor.workshop.customer.adapter.outbound.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CustomerEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private String email;
    private String phoneNumber;
    private String status;
}

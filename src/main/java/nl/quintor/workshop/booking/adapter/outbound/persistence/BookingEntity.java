package nl.quintor.workshop.booking.adapter.outbound.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.quintor.workshop.booking.domain.model.BookingStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BookingEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private UUID customerId;
    private LocalDateTime dateTime;
    private String fromLocation;
    private String toLocation;
    private byte numberOfPassengers;
    private BookingStatus status;
}

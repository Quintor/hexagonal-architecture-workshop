package nl.quintor.workshop.booking.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@AllArgsConstructor
@Builder
public class Booking {
    UUID id;
    UUID customerId;
    LocalDateTime dateTime;
    String fromLocation;
    String toLocation;
    byte numberOfPassengers;
    @Default
    BookingStatus status = BookingStatus.NEW;
}



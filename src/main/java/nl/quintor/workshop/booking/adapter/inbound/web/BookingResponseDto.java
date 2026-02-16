package nl.quintor.workshop.booking.adapter.inbound.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.quintor.workshop.booking.domain.model.BookingStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class BookingResponseDto {
    private UUID bookingId;
    private UUID customerId;
    private LocalDateTime dateTime;
    private String fromLocation;
    private String toLocation;
    private byte numberOfPassengers;
    private BookingStatus status;
}

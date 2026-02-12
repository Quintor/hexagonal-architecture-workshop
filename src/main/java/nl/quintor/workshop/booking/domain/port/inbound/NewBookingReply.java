package nl.quintor.workshop.booking.domain.port.inbound;

import java.time.LocalDateTime;
import java.util.UUID;

public record NewBookingReply(UUID bookingId,
        UUID customerId,
        LocalDateTime dateTime,
        String fromLocation,
        String toLocation,
        byte numberOfPassengers) {
}

package nl.quintor.workshop.booking.domain.port.inbound;

import java.time.LocalDateTime;

public record NewBookingReply(long bookingId,
        long customerId,
        LocalDateTime dateTime,
        String fromLocation,
        String toLocation,
        byte numberOfPassengers) {
}

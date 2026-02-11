package nl.quintor.workshop.booking.domain.inbound;

import java.time.LocalDateTime;

public record NewBookingCommand(String customerEmail, String customerPhoneNumber, LocalDateTime dateTime,
                                String fromLocation,
                                String toLocation,
                                byte numberOfPassengers) {
}

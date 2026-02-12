package nl.quintor.workshop.booking.domain.port.inbound;

import java.time.LocalDateTime;

public record NewBookingCommand(String customerEmail, String customerPhoneNumber, LocalDateTime dateTime,
                                String fromLocation,
                                String toLocation,
                                byte numberOfPassengers) {
}

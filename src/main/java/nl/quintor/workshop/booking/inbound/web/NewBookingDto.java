package nl.quintor.workshop.booking.inbound.web;

import java.time.LocalDateTime;

public record NewBookingDto(CustomerDto customer, LocalDateTime dateTime,
                            String fromLocation,
                            String toLocation,
                            byte numberOfPassengers) {
}

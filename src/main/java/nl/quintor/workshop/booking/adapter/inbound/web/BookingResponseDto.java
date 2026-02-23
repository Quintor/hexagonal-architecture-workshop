package nl.quintor.workshop.booking.adapter.inbound.web;

import nl.quintor.workshop.booking.domain.model.BookingStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record BookingResponseDto(
                UUID bookingId,
                UUID customerId,
                LocalDateTime dateTime,
                String fromLocation,
                String toLocation,
                byte passengerAmount,
                BookingStatus status) {
}

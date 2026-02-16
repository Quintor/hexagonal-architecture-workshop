package nl.quintor.workshop.booking.domain.port.inbound;

import jakarta.validation.Valid;
import nl.quintor.workshop.booking.domain.model.Booking;

public interface BookingApiPort {
    Booking newBooking(@Valid NewBookingCommand command);
}

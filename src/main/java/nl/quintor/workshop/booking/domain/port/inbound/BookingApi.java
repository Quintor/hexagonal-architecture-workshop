package nl.quintor.workshop.booking.domain.port.inbound;

import jakarta.validation.Valid;
import nl.quintor.workshop.booking.domain.model.Booking;

public interface BookingApi {
    Booking newBooking(@Valid NewBookingCommand command);
}

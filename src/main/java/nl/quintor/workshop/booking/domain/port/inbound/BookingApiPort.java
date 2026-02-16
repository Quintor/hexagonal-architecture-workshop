package nl.quintor.workshop.booking.domain.port.inbound;

import nl.quintor.workshop.booking.domain.model.Booking;

public interface BookingApiPort {
    Booking newBooking(NewBookingCommand command);
}

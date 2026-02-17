package nl.quintor.workshop.booking.domain.port.outbound;

import nl.quintor.workshop.booking.domain.model.Booking;

public interface BookingRepository {
    Booking save(Booking booking);
}

package nl.quintor.workshop.booking.domain.port.inbound;

public interface BookingService {
    NewBookingReply newBooking(NewBookingCommand command);
}

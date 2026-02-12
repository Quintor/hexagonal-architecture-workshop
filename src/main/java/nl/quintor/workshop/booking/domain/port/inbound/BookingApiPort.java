package nl.quintor.workshop.booking.domain.port.inbound;

public interface BookingApiPort {
    NewBookingReply newBooking(NewBookingCommand command);
}

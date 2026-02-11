package nl.quintor.workshop.booking.domain.inbound;

public interface BookingService {
    NewBookingReply newBooking(NewBookingCommand command);
}

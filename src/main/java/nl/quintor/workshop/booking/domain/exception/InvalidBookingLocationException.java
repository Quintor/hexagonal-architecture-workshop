package nl.quintor.workshop.booking.domain.exception;

public class InvalidBookingLocationException extends RuntimeException {
    public InvalidBookingLocationException(String message) {
        super(message);
    }
}

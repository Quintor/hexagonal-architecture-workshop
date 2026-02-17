package nl.quintor.workshop.booking.adapter.inbound.web;

import lombok.extern.slf4j.Slf4j;
import nl.quintor.workshop.booking.domain.exception.InvalidBookingLocationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class BookingExceptionHandler {

    @ExceptionHandler(InvalidBookingLocationException.class)
    public ResponseEntity<String> handleInvalidBooking(InvalidBookingLocationException ex) {
        return ResponseEntity.unprocessableContent().body(ex.getMessage());
    }
}

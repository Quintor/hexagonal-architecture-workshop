package nl.quintor.workshop.booking.adapter.inbound.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.quintor.workshop.booking.domain.port.inbound.BookingService;
import nl.quintor.workshop.booking.domain.port.inbound.NewBookingCommand;
import nl.quintor.workshop.booking.domain.port.inbound.NewBookingReply;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<NewBookingReply> createNewBooking(@RequestBody NewBookingCommand newBookingCommand) {
        var createdBooking = bookingService.newBooking(newBookingCommand);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
    }
}

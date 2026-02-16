package nl.quintor.workshop.booking.adapter.inbound.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.quintor.workshop.booking.domain.model.Booking;
import nl.quintor.workshop.booking.domain.port.inbound.BookingApiPort;
import nl.quintor.workshop.booking.domain.port.inbound.NewBookingCommand;
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
    private final BookingApiPort bookingApiPort;

    @PostMapping
    public ResponseEntity<BookingResponseDto> createNewBooking(@Valid @RequestBody BookingPostDto bookingPostDto) {
        var newBookingCommand = new NewBookingCommand(
                bookingPostDto.customerPhoneNumber(),
                bookingPostDto.dateTime(),
                bookingPostDto.fromLocation(),
                bookingPostDto.toLocation(),
                bookingPostDto.numberOfPassengers());

        Booking createdBooking = bookingApiPort.newBooking(newBookingCommand);

        var responseDto = new BookingResponseDto(
                createdBooking.getId(),
                createdBooking.getCustomerId(),
                createdBooking.getDateTime(),
                createdBooking.getFromLocation(),
                createdBooking.getToLocation(),
                createdBooking.getNumberOfPassengers(),
                createdBooking.getStatus());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}

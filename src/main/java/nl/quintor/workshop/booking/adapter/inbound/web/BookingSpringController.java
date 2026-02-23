package nl.quintor.workshop.booking.adapter.inbound.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.quintor.workshop.booking.domain.model.Booking;
import nl.quintor.workshop.booking.domain.port.inbound.BookingApi;
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
public class BookingSpringController {
    private final BookingApi bookingApi;

    @PostMapping
    public ResponseEntity<BookingResponseDto> createNewBooking(
            @Valid @RequestBody BookingPostRequestDto bookingPostRequestDto) {
        var newBookingCommand = new NewBookingCommand(
                bookingPostRequestDto.customerName(),
                bookingPostRequestDto.customerPhoneNumber(),
                bookingPostRequestDto.dateTime(),
                bookingPostRequestDto.fromLocation(),
                bookingPostRequestDto.toLocation(),
                bookingPostRequestDto.numberOfPassengers());

        Booking createdBooking = bookingApi.createBooking(newBookingCommand);

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

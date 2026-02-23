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
        log.info("Boeking aanvraag ontvangen: klant={}, van={}, naar={}", 
                bookingPostRequestDto.customerName(), 
                bookingPostRequestDto.fromLocation(), 
                bookingPostRequestDto.toLocation());
                
        var newBookingCommand = new NewBookingCommand(
                bookingPostRequestDto.customerName(),
                bookingPostRequestDto.customerPhoneNumber(),
                bookingPostRequestDto.dateTime(),
                bookingPostRequestDto.fromLocation(),
                bookingPostRequestDto.toLocation(),
                bookingPostRequestDto.passengerAmount());

        Booking createdBooking = bookingApi.createBooking(newBookingCommand);
        log.info("Boeking succesvol aangemaakt met ID: {}", createdBooking.getId());

        var responseDto = new BookingResponseDto(
                createdBooking.getId(),
                createdBooking.getCustomerId(),
                createdBooking.getDateTime(),
                createdBooking.getFromLocation(),
                createdBooking.getToLocation(),
                createdBooking.getPassengerAmount(),
                createdBooking.getStatus());

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}

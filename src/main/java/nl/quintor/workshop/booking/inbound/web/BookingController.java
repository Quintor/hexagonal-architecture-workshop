package nl.quintor.workshop.booking.inbound.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.quintor.workshop.booking.domain.model.Booking;
import nl.quintor.workshop.booking.application.service.BookingService;
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
    private final BookingDtoMapper bookingDtoMapper;

    @PostMapping
    public ResponseEntity<NewBookingDto> createBooking(@RequestBody NewBookingDto newBookingDto) {
        Booking booking = bookingDtoMapper.toDomain(newBookingDto);
        Booking createdBooking = bookingService.placeBooking(booking);

        NewBookingDto responseDto = bookingDtoMapper.toDto(createdBooking);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}

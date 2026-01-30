package nl.quintor.workshop.booking.domain.service;

import lombok.RequiredArgsConstructor;
import nl.quintor.workshop.booking.domain.model.Booking;
import nl.quintor.workshop.booking.domain.port.outbound.BookingRepository;
import nl.quintor.workshop.booking.domain.port.outbound.CustomerServiceClient;

@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final CustomerServiceClient customerServiceClient;

    public Booking placeBooking(Booking booking) {
        // Dit is de event storming 'Nieuwe klant' policy implementatie (nog zonder error handklibg)
        customerServiceClient.validateOrCreateCustomer(booking.getCustomer());
        return bookingRepository.save(booking);
    }
}

package nl.quintor.workshop.booking.domain.service;

import lombok.RequiredArgsConstructor;
import nl.quintor.workshop.booking.domain.model.Booking;
import nl.quintor.workshop.booking.domain.port.inbound.BookingApi;
import nl.quintor.workshop.booking.domain.port.inbound.NewBookingCommand;
import nl.quintor.workshop.booking.domain.port.outbound.BookingRepository;
import nl.quintor.workshop.booking.domain.port.outbound.CustomerManager;
import nl.quintor.workshop.booking.domain.port.outbound.GetOrCreateCustomerRequest;
import nl.quintor.workshop.common.domain.exception.DomainValidationException;
import org.springframework.validation.annotation.Validated;

@Validated
@RequiredArgsConstructor
public class BookingService implements BookingApi {
    private final BookingRepository bookingRepository;
    private final CustomerManager customerManager;

    @Override
    public Booking createBooking(NewBookingCommand command) {
        validateBookingLocations(command);

        var customerServiceRequest = new GetOrCreateCustomerRequest(command.customerName(),
                command.customerPhoneNumber());
        var customerServiceResponse = customerManager.getOrCreateCustomer(customerServiceRequest);

        var booking = Booking.builder()
                .customerId(customerServiceResponse.customerId())
                .toLocation(command.toLocation())
                .fromLocation(command.fromLocation())
                .dateTime(command.dateTime())
                .numberOfPassengers(command.numberOfPassengers())
                .build();

        return bookingRepository.save(booking);
    }

    private void validateBookingLocations(NewBookingCommand command) {
        if (command.fromLocation().equals(command.toLocation())) {
            throw new DomainValidationException("From location cannot be the same as to location");
        }
    }
}

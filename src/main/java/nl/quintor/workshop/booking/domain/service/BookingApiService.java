package nl.quintor.workshop.booking.domain.service;

import lombok.RequiredArgsConstructor;
import nl.quintor.workshop.booking.domain.port.inbound.BookingApiPort;
import nl.quintor.workshop.booking.domain.port.inbound.NewBookingCommand;
import nl.quintor.workshop.booking.domain.model.Booking;
import nl.quintor.workshop.booking.domain.port.outbound.BookingRepositorySpiPort;
import nl.quintor.workshop.booking.domain.port.outbound.CustomerServiceClient;
import nl.quintor.workshop.booking.domain.port.outbound.GetOrCreateCustomerRequest;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class BookingApiService implements BookingApiPort {
    private final BookingRepositorySpiPort bookingRepositorySpiPort;
    private final CustomerServiceClient customerServiceClient;

    @Override
    @Transactional
    public Booking newBooking(NewBookingCommand command) {
        validateNewBookingCommand(command);

        var customerServiceRequest = new GetOrCreateCustomerRequest(command.customerPhoneNumber());
        var customerServiceResponse = customerServiceClient.getOrCreateCustomer(customerServiceRequest);

        var booking = Booking.builder()
                .customerId(customerServiceResponse.customerId())
                .toLocation(command.toLocation())
                .fromLocation(command.fromLocation())
                .dateTime(command.dateTime())
                .numberOfPassengers(command.numberOfPassengers())
                .build();

        return bookingRepositorySpiPort.save(booking);
    }

    private void validateNewBookingCommand(NewBookingCommand command) {
        if (command.customerPhoneNumber() == null || command.customerPhoneNumber().isBlank()) {
            throw new IllegalArgumentException("Customer phone number is required");
        }
        if (command.dateTime() == null) {
            throw new IllegalArgumentException("Date and time is required");
        }
        if (command.fromLocation() == null || command.fromLocation().isBlank()) {
            throw new IllegalArgumentException("From location is required");
        }
        if (command.toLocation() == null || command.toLocation().isBlank()) {
            throw new IllegalArgumentException("To location is required");
        }
        if (command.numberOfPassengers() <= 0) {
            throw new IllegalArgumentException("Number of passengers must be positive");
        }
    }
}

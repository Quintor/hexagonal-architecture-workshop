package nl.quintor.workshop.booking.domain.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nl.quintor.workshop.booking.domain.port.inbound.BookingApiPort;
import nl.quintor.workshop.booking.domain.port.inbound.NewBookingCommand;
import nl.quintor.workshop.booking.domain.model.Booking;
import nl.quintor.workshop.booking.domain.port.outbound.BookingRepositorySpiPort;
import nl.quintor.workshop.booking.domain.port.outbound.CustomerServiceClient;
import nl.quintor.workshop.booking.domain.port.outbound.GetOrCreateCustomerRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Validated
@RequiredArgsConstructor
public class BookingApiService implements BookingApiPort {
    private final BookingRepositorySpiPort bookingRepositorySpiPort;
    private final CustomerServiceClient customerServiceClient;

    @Override
    @Transactional
    public Booking newBooking(NewBookingCommand command) {
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
}

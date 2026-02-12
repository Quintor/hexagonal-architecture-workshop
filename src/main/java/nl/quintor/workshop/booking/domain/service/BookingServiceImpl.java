package nl.quintor.workshop.booking.domain.service;

import lombok.RequiredArgsConstructor;
import nl.quintor.workshop.booking.domain.port.inbound.BookingService;
import nl.quintor.workshop.booking.domain.port.inbound.NewBookingCommand;
import nl.quintor.workshop.booking.domain.port.inbound.NewBookingReply;
import nl.quintor.workshop.booking.domain.model.Booking;
import nl.quintor.workshop.booking.domain.port.outbound.BookingRepository;
import nl.quintor.workshop.booking.domain.port.outbound.CustomerServiceClient;
import nl.quintor.workshop.booking.domain.port.outbound.GetOrCreateCustomerRequest;

@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final CustomerServiceClient customerServiceClient;
    private final BookingMapper bookingMapper;


    @Override
    public NewBookingReply newBooking(NewBookingCommand command) {
        var customerServiceRequest = new GetOrCreateCustomerRequest(command.customerEmail(), command.customerPhoneNumber());
        var customerServiceResponse = customerServiceClient.GetOrCreateCustomer(customerServiceRequest);

        var booking = Booking.builder()
                .customerId(customerServiceResponse.customerId())
                .toLocation(command.toLocation())
                .fromLocation(command.fromLocation())
                .dateTime(command.dateTime())
                .numberOfPassengers(command.numberOfPassengers())
                .build();

        var savedBooking = bookingRepository.save(booking);
        return bookingMapper.toNewBookingReply(savedBooking);
    }
}

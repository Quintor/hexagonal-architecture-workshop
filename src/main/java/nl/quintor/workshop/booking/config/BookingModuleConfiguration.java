package nl.quintor.workshop.booking.config;

import nl.quintor.workshop.booking.adapter.outbound.persistence.BookingRepositoryImpl;
import nl.quintor.workshop.booking.adapter.outbound.service.CustomerInternalServiceAdapter;
import nl.quintor.workshop.booking.domain.port.inbound.BookingService;
import nl.quintor.workshop.booking.domain.port.outbound.BookingRepository;
import nl.quintor.workshop.booking.domain.port.outbound.CustomerServiceClient;
import nl.quintor.workshop.booking.domain.service.BookingMapper;
import nl.quintor.workshop.booking.domain.service.BookingServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookingModuleConfiguration {

    @Bean
    public BookingRepository bookingRepository(BookingRepositoryImpl bookingRepositoryImpl) {
        return bookingRepositoryImpl;
    }

    @Bean
    public CustomerServiceClient customerServiceClient(CustomerInternalServiceAdapter customerInternalServiceAdapter) {
        return customerInternalServiceAdapter;
    }

    @Bean
    public BookingService bookingService(
            BookingRepository bookingRepository,
            CustomerServiceClient customerServiceClient, BookingMapper bookingMapper) {
        return new BookingServiceImpl(bookingRepository, customerServiceClient, bookingMapper);
    }
}

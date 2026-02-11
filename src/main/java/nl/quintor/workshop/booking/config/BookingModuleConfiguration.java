package nl.quintor.workshop.booking.config;

import nl.quintor.workshop.booking.domain.inbound.BookingService;
import nl.quintor.workshop.booking.domain.outbound.BookingRepository;
import nl.quintor.workshop.booking.domain.outbound.CustomerServiceClient;
import nl.quintor.workshop.booking.domain.service.BookingMapper;
import nl.quintor.workshop.booking.domain.service.BookingServiceImpl;
import nl.quintor.workshop.booking.outbound.persistence.BookingEntityMapper;
import nl.quintor.workshop.booking.outbound.persistence.BookingRepositoryImpl;
import nl.quintor.workshop.booking.outbound.persistence.SpringDataBookingRepository;
import nl.quintor.workshop.booking.outbound.service.CustomerInternalServiceAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookingModuleConfiguration {

    @Bean
    public BookingRepositoryImpl bookingRepositoryImpl(
            BookingEntityMapper bookingEntityMapper,
            SpringDataBookingRepository springDataBookingRepository) {
        return new BookingRepositoryImpl(bookingEntityMapper, springDataBookingRepository);
    }

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

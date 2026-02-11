package nl.quintor.workshop.booking.config;

import nl.quintor.workshop.booking.domain.outbound.BookingRepository;
import nl.quintor.workshop.booking.domain.outbound.CustomerServiceClient;
import nl.quintor.workshop.booking.domain.service.BookingService;
import nl.quintor.workshop.booking.outbound.persistence.BookingEntityMapper;
import nl.quintor.workshop.booking.outbound.persistence.BookingRepositoryImpl;
import nl.quintor.workshop.booking.outbound.persistence.SpringDataBookingRepository;
import nl.quintor.workshop.booking.outbound.services.CustomerServiceClientAdapter;
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
    public CustomerServiceClient customerServiceClient(CustomerServiceClientAdapter customerServiceClientAdapter) {
        return customerServiceClientAdapter;
    }

    @Bean
    public BookingService bookingService(
            BookingRepository bookingRepository,
            CustomerServiceClient customerServiceClient) {
        return new BookingService(bookingRepository, customerServiceClient);
    }
}

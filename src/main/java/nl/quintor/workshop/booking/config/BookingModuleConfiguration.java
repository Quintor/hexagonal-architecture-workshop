package nl.quintor.workshop.booking.config;

import nl.quintor.workshop.booking.domain.port.inbound.BookingApiPort;
import nl.quintor.workshop.booking.domain.port.outbound.BookingRepositorySpiPort;
import nl.quintor.workshop.booking.domain.port.outbound.CustomerServiceClient;
import nl.quintor.workshop.booking.domain.service.BookingApiService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookingModuleConfiguration {
    @Bean
    public BookingApiPort bookingService(
            BookingRepositorySpiPort bookingRepositorySpiPort,
            CustomerServiceClient customerServiceClient) {
        return new BookingApiService(bookingRepositorySpiPort, customerServiceClient);
    }
}

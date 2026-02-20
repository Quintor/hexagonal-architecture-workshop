package nl.quintor.workshop.booking.config;

import nl.quintor.workshop.booking.domain.port.inbound.BookingApi;
import nl.quintor.workshop.booking.domain.port.outbound.BookingRepository;
import nl.quintor.workshop.booking.domain.port.outbound.CustomerManager;
import nl.quintor.workshop.booking.domain.service.BookingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class BookingModuleConfiguration {
    @Bean
    public BookingApi bookingApi(
            BookingRepository bookingRepository,
            CustomerManager customerManager) {
        return new BookingService(bookingRepository, customerManager);
    }

    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }
}

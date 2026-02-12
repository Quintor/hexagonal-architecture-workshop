package nl.quintor.workshop.customer.config;

import nl.quintor.workshop.customer.domain.port.inbound.CustomerApiPort;
import nl.quintor.workshop.customer.domain.port.outbound.CustomerRepositorySpiPort;
import nl.quintor.workshop.customer.domain.service.CustomerApiService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerModuleConfiguration {


    @Bean
    public CustomerApiPort customerService(CustomerRepositorySpiPort customerRepositorySpiPort) {
        return new CustomerApiService(customerRepositorySpiPort);
    }
}

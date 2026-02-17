package nl.quintor.workshop.customer.config;

import nl.quintor.workshop.customer.domain.port.inbound.CustomerApi;
import nl.quintor.workshop.customer.domain.port.outbound.CustomerRepository;
import nl.quintor.workshop.customer.domain.service.CustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerModuleConfiguration {


    @Bean
    public CustomerApi customerApi(CustomerRepository customerRepository) {
        return new CustomerService(customerRepository);
    }
}

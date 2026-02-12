package nl.quintor.workshop.customer.config;

import nl.quintor.workshop.customer.domain.port.inbound.CustomerService;
import nl.quintor.workshop.customer.domain.port.outbound.CustomerRepository;
import nl.quintor.workshop.customer.domain.service.CustomerServiceImpl;
import nl.quintor.workshop.customer.adapter.outbound.persistence.CustomerRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerModuleConfiguration {

    @Bean
    public CustomerRepository customerRepository(CustomerRepositoryImpl customerRepositoryImpl) {
        return customerRepositoryImpl;
    }

    @Bean
    public CustomerService customerService(CustomerRepository customerRepository) {
        return new CustomerServiceImpl(customerRepository);
    }
}

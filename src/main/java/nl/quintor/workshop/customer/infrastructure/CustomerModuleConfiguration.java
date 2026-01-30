package nl.quintor.workshop.customer.infrastructure;

import nl.quintor.workshop.customer.api.CustomerPublicService;
import nl.quintor.workshop.customer.infrastructure.inbound.services.CustomerPublicServiceImpl;
import nl.quintor.workshop.customer.domain.port.outbound.CustomerRepository;
import nl.quintor.workshop.customer.domain.service.CustomerService;
import nl.quintor.workshop.customer.infrastructure.outbound.persistence.CustomerEntityMapper;
import nl.quintor.workshop.customer.infrastructure.outbound.persistence.CustomerRepositoryImpl;
import nl.quintor.workshop.customer.infrastructure.outbound.persistence.SpringDataCustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerModuleConfiguration {

    @Bean
    public CustomerRepositoryImpl customerRepositoryImpl(
            CustomerEntityMapper customerEntityMapper,
            SpringDataCustomerRepository springDataCustomerRepository) {
        return new CustomerRepositoryImpl(customerEntityMapper, springDataCustomerRepository);
    }

    @Bean
    public CustomerRepository customerRepository(CustomerRepositoryImpl customerRepositoryImpl) {
        return customerRepositoryImpl;
    }

    @Bean
    public CustomerService customerService(CustomerRepository customerRepository) {
        return new CustomerService(customerRepository);
    }

    @Bean
    public CustomerPublicService customerPublicService(CustomerService customerService) {
        return new CustomerPublicServiceImpl(customerService);
    }
}

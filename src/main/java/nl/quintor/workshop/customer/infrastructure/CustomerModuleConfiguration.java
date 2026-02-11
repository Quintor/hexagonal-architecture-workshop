package nl.quintor.workshop.customer.infrastructure;

import nl.quintor.workshop.customer.domain.inbound.CustomerService;
import nl.quintor.workshop.customer.domain.service.CustomerServiceImpl;
import nl.quintor.workshop.customer.domain.outbound.CustomerRepository;
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
    public nl.quintor.workshop.customer.application.service.CustomerService customerService(CustomerRepository customerRepository) {
        return new nl.quintor.workshop.customer.application.service.CustomerService(customerRepository);
    }

    @Bean
    public CustomerService customerPublicService(nl.quintor.workshop.customer.application.service.CustomerService customerService) {
        return new CustomerServiceImpl(customerService);
    }
}

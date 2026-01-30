package nl.quintor.workshop.booking.infrastructure.outbound.services;

import nl.quintor.workshop.booking.domain.model.Customer;
import nl.quintor.workshop.customer.api.CustomerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExternalCustomerDtoMapper {
    CustomerDto toDTO(Customer customer);

    Customer fromDTO(CustomerDto customerDto);
}

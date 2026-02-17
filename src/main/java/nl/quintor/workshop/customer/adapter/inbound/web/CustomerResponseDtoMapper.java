package nl.quintor.workshop.customer.adapter.inbound.web;

import nl.quintor.workshop.customer.domain.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerResponseDtoMapper {
    CustomerResponseDto toResponseDto(Customer customer);
}

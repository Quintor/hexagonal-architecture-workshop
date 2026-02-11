package nl.quintor.workshop.customer.domain.service;

import nl.quintor.workshop.customer.domain.inbound.CustomerDto;
import nl.quintor.workshop.customer.domain.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ServiceCustomerDtoMapper {

    ServiceCustomerDtoMapper INSTANCE = Mappers.getMapper(ServiceCustomerDtoMapper.class);

    Customer toDomain(CustomerDto customer);

    CustomerDto toDto(Customer entity);
}
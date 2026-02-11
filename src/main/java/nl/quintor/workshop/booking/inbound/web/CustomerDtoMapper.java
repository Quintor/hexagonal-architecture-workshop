package nl.quintor.workshop.booking.inbound.web;

import nl.quintor.workshop.booking.domain.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerDtoMapper {
    CustomerDtoMapper INSTANCE = Mappers.getMapper(CustomerDtoMapper.class);

    Customer toDomain(CustomerDto customerDto);

    CustomerDto toDto(Customer customer);
}

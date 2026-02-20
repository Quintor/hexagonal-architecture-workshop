package nl.quintor.workshop.booking.adapter.outbound.manager.rest;

import nl.quintor.workshop.booking.domain.port.outbound.GetOrCreateCustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface RestCustomerDtoMapper {
    @Mapping(source = "id", target = "customerId")
    GetOrCreateCustomerResponse toGetOrCreateCustomerResponse(RestCustomerResponseDto dto);
}


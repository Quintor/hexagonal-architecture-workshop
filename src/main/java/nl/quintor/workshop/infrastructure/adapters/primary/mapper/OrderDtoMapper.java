package nl.quintor.workshop.infrastructure.adapters.primary.mapper;

import nl.quintor.workshop.domain.model.Order;
import nl.quintor.workshop.infrastructure.adapters.primary.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderDtoMapper {

    OrderDtoMapper INSTANCE = Mappers.getMapper(OrderDtoMapper.class);

    Order toDomain(OrderDto order);

    OrderDto toDto(Order entity);
}
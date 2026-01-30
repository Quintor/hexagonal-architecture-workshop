package nl.quintor.workshop.order.infrastructure.adapters.inbound.mapper;

import nl.quintor.workshop.order.domain.model.Order;
import nl.quintor.workshop.order.infrastructure.adapters.inbound.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderDtoMapper {

    OrderDtoMapper INSTANCE = Mappers.getMapper(OrderDtoMapper.class);

    Order toDomain(OrderDto order);

    OrderDto toDto(Order entity);
}
package nl.quintor.workshop.infrastructure.adapters.secondairy.persistence.mapper;

import nl.quintor.workshop.domain.model.Order;
import nl.quintor.workshop.infrastructure.adapters.secondairy.persistence.jpa.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderEntityMapper {

    OrderEntityMapper INSTANCE = Mappers.getMapper(OrderEntityMapper.class);

    // Domain -> Entity
    OrderEntity toEntity(Order order);

    // Entity -> Domain
    Order toDomain(OrderEntity entity);
}

package nl.quintor.workshop.customer.infrastructure.outbound.persistence;

import nl.quintor.workshop.customer.domain.model.Customer;
import nl.quintor.workshop.customer.domain.model.CustomerStatus;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {

    CustomerEntityMapper INSTANCE = Mappers.getMapper(CustomerEntityMapper.class);

    // Domain -> Entity
    CustomerEntity toEntity(Customer customer);

    // Entity -> Domain
    Customer toDomain(CustomerEntity entity);

    default String map(CustomerStatus status) {
        return status != null ? status.name() : null;
    }

    default CustomerStatus map(String status) {
        return status != null ? CustomerStatus.valueOf(status) : null;
    }
}

package nl.quintor.workshop.customer.outbound.persistence;

import nl.quintor.workshop.customer.domain.model.Customer;
import nl.quintor.workshop.customer.domain.model.CustomerStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {
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

package nl.quintor.workshop.booking.infrastructure.outbound.persistence;

import nl.quintor.workshop.booking.domain.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingCustomerEntityMapper {

    CustomerEntity toEntity(Customer customer);

    Customer toDomain(CustomerEntity entity);
}

package nl.quintor.workshop.booking.infrastructure.outbound.persistence;

import nl.quintor.workshop.booking.domain.model.Booking;
import nl.quintor.workshop.booking.domain.model.BookingStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = BookingCustomerEntityMapper.class)
public interface BookingEntityMapper {

    BookingEntityMapper INSTANCE = Mappers.getMapper(BookingEntityMapper.class);

    @Mapping(source = "customer", target = "customer")
    BookingEntity toEntity(Booking booking);

    @Mapping(source = "customer", target = "customer")
    Booking toDomain(BookingEntity entity);

    default String map(BookingStatus status) {
        return status != null ? status.name() : null;
    }

    default BookingStatus map(String status) {
        return status != null ? BookingStatus.valueOf(status) : null;
    }
}

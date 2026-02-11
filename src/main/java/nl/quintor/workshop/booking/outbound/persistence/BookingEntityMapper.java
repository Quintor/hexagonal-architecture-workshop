package nl.quintor.workshop.booking.outbound.persistence;

import nl.quintor.workshop.booking.domain.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookingEntityMapper {
    BookingEntityMapper INSTANCE = Mappers.getMapper(BookingEntityMapper.class);

    BookingEntity toEntity(Booking booking);

    Booking toDomain(BookingEntity entity);
}

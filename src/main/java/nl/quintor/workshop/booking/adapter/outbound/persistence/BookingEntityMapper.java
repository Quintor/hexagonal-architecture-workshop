package nl.quintor.workshop.booking.adapter.outbound.persistence;

import nl.quintor.workshop.booking.domain.model.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingEntityMapper {

    BookingEntity toEntity(Booking booking);

    Booking toDomain(BookingEntity entity);
}

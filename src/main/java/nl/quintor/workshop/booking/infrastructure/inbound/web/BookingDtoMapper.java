package nl.quintor.workshop.booking.infrastructure.inbound.web;

import nl.quintor.workshop.booking.domain.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = CustomerDtoMapper.class)
public interface BookingDtoMapper {
    BookingDtoMapper INSTANCE = Mappers.getMapper(BookingDtoMapper.class);

    @Mapping(source = "customer", target = "customer")
    Booking toDomain(NewBookingDto newBookingDto);

    @Mapping(source = "customer", target = "customer")
    NewBookingDto toDto(Booking booking);
}

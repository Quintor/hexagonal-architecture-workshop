package nl.quintor.workshop.booking.domain.service;

import nl.quintor.workshop.booking.domain.inbound.NewBookingReply;
import nl.quintor.workshop.booking.domain.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);


    @Mapping(target = "bookingId", source = "id")
    NewBookingReply toNewBookingReply(Booking booking);
}

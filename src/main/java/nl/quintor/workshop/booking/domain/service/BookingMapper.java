package nl.quintor.workshop.booking.domain.service;

import nl.quintor.workshop.booking.domain.inbound.NewBookingReply;
import nl.quintor.workshop.booking.domain.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {


    @Mapping(target = "bookingId", source = "id")
    NewBookingReply toNewBookingReply(Booking booking);
}

package nl.quintor.workshop.booking.adapter.outbound.persistence;

import lombok.RequiredArgsConstructor;
import nl.quintor.workshop.booking.domain.model.Booking;
import nl.quintor.workshop.booking.domain.port.outbound.BookingRepositorySpiPort;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookingRepositoryJpaAdapter implements BookingRepositorySpiPort {
    private final BookingEntityMapper bookingEntityMapper;
    private final SpringDataBookingRepository springDataBookingRepository;

    @Override
    public Booking save(Booking booking) {
        var bookingEntity = bookingEntityMapper.toEntity(booking);
        var savedEntity = springDataBookingRepository.save(bookingEntity);
        return bookingEntityMapper.toDomain(savedEntity);
    }
}

package nl.quintor.workshop.booking.outbound.persistence;

import lombok.RequiredArgsConstructor;
import nl.quintor.workshop.booking.domain.model.Booking;
import nl.quintor.workshop.booking.domain.outbound.BookingRepository;

@RequiredArgsConstructor
public class BookingRepositoryImpl implements BookingRepository {
    private final BookingEntityMapper bookingEntityMapper;
    private final SpringDataBookingRepository springDataBookingRepository;

    @Override
    public Booking save(Booking booking) {
        var bookingEntity = bookingEntityMapper.toEntity(booking);
        var savedEntity = springDataBookingRepository.save(bookingEntity);
        return bookingEntityMapper.toDomain(savedEntity);
    }
}

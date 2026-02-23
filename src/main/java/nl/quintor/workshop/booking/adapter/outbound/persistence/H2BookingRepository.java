package nl.quintor.workshop.booking.adapter.outbound.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.quintor.workshop.booking.domain.model.Booking;
import nl.quintor.workshop.booking.domain.port.outbound.BookingRepository;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class H2BookingRepository implements BookingRepository {
    private final BookingEntityMapper bookingEntityMapper;
    private final SpringDataBookingRepository springDataBookingRepository;

    @Override
    public Booking save(Booking booking) {
        log.info("[H2BookingRepository] Persisteren van boeking voor customer ID: {}", booking.getCustomerId());
        
        var bookingEntity = bookingEntityMapper.toEntity(booking);
        var savedEntity = springDataBookingRepository.save(bookingEntity);
        log.info("[H2BookingRepository] Boeking opgeslagen met ID: {}", savedEntity.getId());
        
        return bookingEntityMapper.toDomain(savedEntity);
    }
}

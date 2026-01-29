package nl.quintor.workshop.infrastructure.adapters.secondairy.persistence;

import lombok.RequiredArgsConstructor;
import nl.quintor.workshop.domain.model.Order;
import nl.quintor.workshop.domainservice.repository.OrderRepository;
import nl.quintor.workshop.infrastructure.adapters.secondairy.persistence.mapper.OrderEntityMapper;
import nl.quintor.workshop.infrastructure.adapters.secondairy.persistence.spring.SpringDataOrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class JpaOrderRepository implements OrderRepository {
    private final OrderEntityMapper orderEntityMapper;
    private final SpringDataOrderRepository springDataOrderRepository;

    @Override
    public List<Order> findOrders() {
        return springDataOrderRepository.findAll().stream()
                .map(orderEntityMapper::toDomain)
                .toList();
    }

}
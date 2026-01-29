package nl.quintor.workshop.infrastructure.adapters.secondary.persistence.spring;

import nl.quintor.workshop.infrastructure.adapters.secondary.persistence.jpa.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataOrderRepository extends JpaRepository<OrderEntity, Long> {
}
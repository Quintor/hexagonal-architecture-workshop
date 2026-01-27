package nl.quintor.workshop.infrastructure.adapters.secondairy.persistence.spring;

import nl.quintor.workshop.infrastructure.adapters.secondairy.persistence.jpa.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataOrderRepository extends JpaRepository<OrderEntity, Long> {
}
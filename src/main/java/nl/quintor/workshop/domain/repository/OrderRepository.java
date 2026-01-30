package nl.quintor.workshop.domain.repository;

import nl.quintor.workshop.domain.model.Order;

import java.util.List;

public interface OrderRepository {
    List<Order> findOrders();
}

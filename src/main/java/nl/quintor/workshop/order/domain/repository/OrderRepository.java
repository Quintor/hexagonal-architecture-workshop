package nl.quintor.workshop.order.domain.repository;

import nl.quintor.workshop.order.domain.model.Order;

import java.util.List;

public interface OrderRepository {
    List<Order> findOrders();
}

package nl.quintor.workshop.order.application.service;

import lombok.RequiredArgsConstructor;
import nl.quintor.workshop.order.domain.model.Order;
import nl.quintor.workshop.order.domain.repository.OrderRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findOrders();
    }
}

package nl.quintor.workshop.infrastructure.adapters.primary.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.quintor.workshop.application.OrderService;
import nl.quintor.workshop.infrastructure.adapters.primary.dto.OrderDto;
import nl.quintor.workshop.infrastructure.adapters.primary.mapper.OrderDtoMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class HelloWorldController {
    private final OrderService orderService;
    private final OrderDtoMapper orderDtoMapper;

    @GetMapping
    public List<OrderDto> hello() {
        log.info("ophalen orders");
        return orderService.findAll().stream()
                .map(orderDtoMapper::toDto)
                .toList();
    }
}

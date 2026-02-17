package nl.quintor.workshop.customer.adapter.inbound.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.quintor.workshop.customer.domain.port.inbound.CustomerApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerSpringController {
        private final CustomerApi customerApi;
        private final CustomerResponseDtoMapper customerResponseDtoMapper;

        @GetMapping
        public List<CustomerResponseDto> getAllCustomers() {
                return customerApi.getAllCustomers()
                                .stream()
                                .map(customerResponseDtoMapper::toResponseDto)
                                .toList();
        }
}

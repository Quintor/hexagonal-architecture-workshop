package nl.quintor.workshop.customer.adapter.inbound.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.quintor.workshop.customer.domain.port.inbound.CustomerApi;
import nl.quintor.workshop.customer.domain.port.inbound.GetOrCreateCustomerCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        @PutMapping("/{phoneNumber}")
        public ResponseEntity<CustomerResponseDto> getOrCreateCustomer(@PathVariable String phoneNumber) {
                var command = new GetOrCreateCustomerCommand(phoneNumber);
                var reply = customerApi.getOrCreateCustomer(command);
                var responseDto = new CustomerResponseDto(reply.customerId(), reply.phoneNumber());
              
                return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
}

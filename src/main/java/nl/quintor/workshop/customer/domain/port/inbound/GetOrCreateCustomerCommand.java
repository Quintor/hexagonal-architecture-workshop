package nl.quintor.workshop.customer.domain.port.inbound;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record GetOrCreateCustomerCommand(
        @NotBlank(message = "Customer name is required") String name,
        @Pattern(regexp = "^(\\+31|0)6\\d{8}$", message = "Phone number must be a valid Dutch mobile number (e.g., 0612345678 or +31612345678)") String phoneNumber) {
}

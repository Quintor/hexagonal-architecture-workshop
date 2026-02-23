package nl.quintor.workshop.customer.adapter.inbound.web;

import jakarta.validation.constraints.NotBlank;

public record CustomerPostRequestDto(
        @NotBlank(message = "Customer name is required") String name) {
}

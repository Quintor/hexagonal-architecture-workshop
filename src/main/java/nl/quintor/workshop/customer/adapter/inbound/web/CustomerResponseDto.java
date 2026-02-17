package nl.quintor.workshop.customer.adapter.inbound.web;

import java.util.UUID;

public record CustomerResponseDto(
                UUID id,
                String phoneNumber) {
}

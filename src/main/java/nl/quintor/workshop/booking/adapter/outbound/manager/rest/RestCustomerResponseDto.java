package nl.quintor.workshop.booking.adapter.outbound.manager.rest;

import java.util.UUID;

public record RestCustomerResponseDto(UUID id,
        String name,
        String phoneNumber) {
}

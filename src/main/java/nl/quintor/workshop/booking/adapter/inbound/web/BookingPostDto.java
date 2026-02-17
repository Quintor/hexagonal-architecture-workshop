package nl.quintor.workshop.booking.adapter.inbound.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record BookingPostDto(
        @NotBlank(message = "Customer phone number is required") @JsonProperty(required = true) String customerPhoneNumber,
        @NotNull(message = "Date and time is required") @JsonProperty(required = true) LocalDateTime dateTime,
        @NotBlank(message = "From location is required") @JsonProperty(required = true) String fromLocation,
        @NotBlank(message = "To location is required") @JsonProperty(required = true) String toLocation,
        @Positive(message = "Number of passengers must be positive") @JsonProperty(required = true) byte numberOfPassengers) {
}

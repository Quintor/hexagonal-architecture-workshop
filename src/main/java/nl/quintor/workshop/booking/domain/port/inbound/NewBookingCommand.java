package nl.quintor.workshop.booking.domain.port.inbound;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record NewBookingCommand(
        @NotBlank(message = "Customer name is required") String customerName,
        @NotBlank(message = "Customer phone number is required") String customerPhoneNumber,
        @NotNull(message = "Date and time is required") LocalDateTime dateTime,
        @NotBlank(message = "From location is required") String fromLocation,
        @NotBlank(message = "To location is required") String toLocation,
        @Positive(message = "Number of passengers must be positive") byte passengerAmount) {
}

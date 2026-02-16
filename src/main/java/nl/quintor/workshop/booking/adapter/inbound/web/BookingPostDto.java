package nl.quintor.workshop.booking.adapter.inbound.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookingPostDto {
    @NotBlank(message = "Customer phone number is required")
    @JsonProperty(required = true)
    private String customerPhoneNumber;

    @NotNull(message = "Date and time is required")
    @JsonProperty(required = true)
    private LocalDateTime dateTime;

    @NotBlank(message = "From location is required")
    @JsonProperty(required = true)
    private String fromLocation;

    @NotBlank(message = "To location is required")
    @JsonProperty(required = true)
    private String toLocation;

    @Positive(message = "Number of passengers must be positive")
    @JsonProperty(required = true)
    private byte numberOfPassengers;
}

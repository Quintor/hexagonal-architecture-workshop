package nl.quintor.workshop.common.adapter.inbound.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponseDto {
    @JsonProperty("field")
    private String field;

    @JsonProperty("message")
    private String message;
}

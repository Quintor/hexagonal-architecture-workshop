package nl.quintor.workshop.common.adapter.inbound.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponseDto>> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex) {

        List<ErrorResponseDto> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ErrorResponseDto(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<List<ErrorResponseDto>> handleIllegalArgument(
            IllegalArgumentException ex) {

        List<ErrorResponseDto> errors = List.of(
                new ErrorResponseDto("error", ex.getMessage()));

        return ResponseEntity.badRequest().body(errors);
    }
}

package nl.quintor.workshop.common.adapter.inbound.web;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import nl.quintor.workshop.common.domain.exception.DomainValidationException;
import org.springframework.http.HttpStatus;
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

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<ErrorResponseDto>> handleConstraintViolation(
            ConstraintViolationException ex) {

        List<ErrorResponseDto> errors = ex.getConstraintViolations()
                .stream()
                .map(violation -> new ErrorResponseDto(
                        violation.getPropertyPath().toString(),
                        violation.getMessage()))
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDto> handleRuntimeException(RuntimeException ex) {
        log.error("Unexpected technical error occurred", ex);

        ErrorResponseDto error = new ErrorResponseDto("error", "An unexpected error occurred");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(DomainValidationException.class)
    public ResponseEntity<String> handleInvalidDomainException (DomainValidationException ex) {
        return ResponseEntity.unprocessableContent().body(ex.getMessage());
    }
}

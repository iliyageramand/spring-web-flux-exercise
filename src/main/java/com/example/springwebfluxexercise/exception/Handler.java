package com.example.springwebfluxexercise.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class Handler extends ResponseEntityExceptionHandler {

    @Override
    @NonNull
    protected Mono<ResponseEntity<Object>> handleWebExchangeBindException(@NonNull WebExchangeBindException ex,
                                                                          @NonNull HttpHeaders headers,
                                                                          @NonNull HttpStatusCode status,
                                                                          @NonNull ServerWebExchange exchange) {
        List<Map<String, Object>> errors = ex.getFieldErrors().stream()
                .map(fieldError -> {
                    Map<String, Object> error = new HashMap<>();
                    error.put("field", fieldError.getField());
                    error.put("message", fieldError.getDefaultMessage());
                    return error;
                })
                .toList();
        ex.getBody().setProperty("errors", errors);
        return super.handleWebExchangeBindException(ex, headers, status, exchange);
    }
}

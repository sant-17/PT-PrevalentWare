package com.prevalentware.prueba_tecnica.infrastructure.exceptionhandler;

import com.prevalentware.prueba_tecnica.domain.exception.DateOrderViolationException;
import com.prevalentware.prueba_tecnica.domain.exception.LogNotFoundException;
import com.prevalentware.prueba_tecnica.infrastructure.utils.APIResponse;
import com.prevalentware.prueba_tecnica.infrastructure.utils.Constant;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerAdvisor {

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream().map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<APIResponse<String>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        APIResponse<String> apiResponse = APIResponse.<String>builder()
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .status(Constant.RESULT_KO)
                .message(ex.getMessage())
                .internalCode("GENERIC-ERROR")
                .data(null)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getHttpStatus()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<APIResponse<String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        APIResponse<String> apiResponse = APIResponse.<String>builder()
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .status(Constant.RESULT_KO)
                .message(ex.getMessage())
                .internalCode("GENERIC-ERROR")
                .data(null)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getHttpStatus()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<APIResponse<String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        APIResponse<String> apiResponse = APIResponse.<String>builder()
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .status(Constant.RESULT_KO)
                .message(ex.getMessage())
                .internalCode("GENERIC-ERROR")
                .data(null)
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getHttpStatus()));
    }

    @ExceptionHandler(LogNotFoundException.class)
    public ResponseEntity<APIResponse<String>> handleLogNotFoundException(LogNotFoundException ex) {
        APIResponse<String> apiResponse = APIResponse.notFound(
                null,
                Constant.getLogResponseHashMap(),
                ex.getMessage(),
                Constant.LOG_RESPONSE_CODE_PREFIX.concat("1")
        );

        return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getHttpStatus()));
    }

    @ExceptionHandler(DateOrderViolationException.class)
    public ResponseEntity<APIResponse<String>> handleDateOrderViolationException(DateOrderViolationException ex) {
        APIResponse<String> apiResponse = APIResponse.notFound(
                null,
                Constant.getLogResponseHashMap(),
                ex.getMessage(),
                Constant.LOG_RESPONSE_CODE_PREFIX.concat("1")
        );

        return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getHttpStatus()));
    }
}

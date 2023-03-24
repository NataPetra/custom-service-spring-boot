package it.academy.rest.except;

import it.academy.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionGlobal extends Throwable {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

    //    400
    @ExceptionHandler(value = {NumberFormatException.class})
    public ResponseEntity<List<ExceptionErrorDTO>> ArgumentUserNotValidException(
            ValidationUserException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(List.of(new ExceptionErrorDTO(e.getMessage())));
    }

    //    409
    @ExceptionHandler(value = {UserAlreadyExistException.class})
    public ResponseEntity<List<ExceptionErrorDTO>> ArgumentUserAlreadyExistException(
            RuntimeException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(List.of(new ExceptionErrorDTO(e.getMessage())));
    }

    //    500
    @ExceptionHandler()
    public ResponseEntity<List<ExceptionErrorDTO>> onHttpMessageNotReadableException(
            HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(List.of(new ExceptionErrorDTO(e.getMessage())));
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class,
            MethodArgumentConversionNotSupportedException.class})
    public ResponseEntity<List<ExceptionErrorDTO>> onArgumentTypeMismatchException(
            HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(List.of(new ExceptionErrorDTO(e.getMessage())));
    }

    @ExceptionHandler
    public ResponseEntity<List<ExceptionErrorDTO>> handler(Throwable e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(List.of(new ExceptionErrorDTO(e.getMessage())));
    }

}

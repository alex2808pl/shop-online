package de.telran.shoponline.controller.advice;

import de.telran.shoponline.exceptions.NotFoundInDbException;
import de.telran.shoponline.exceptions.ResponseException;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AdviceController {
    @ExceptionHandler(NotFoundInDbException.class)
    public ResponseEntity<ErrorMessage> handleException(NotFoundInDbException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(exception.getMessage()));
    }

    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<ErrorMessage> handleException(ResponseException exception) {
        return ResponseEntity
                .status(HttpStatus.I_AM_A_TEAPOT)
                .body(new ErrorMessage("Извините, что-то пошло не так, попробуйте снова!"));
    }

//    // по умолчанию для всех остальных исключений
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorMessage> handleException(Exception exception) {
//        return ResponseEntity
//                .status(HttpStatus.I_AM_A_TEAPOT)
//                .body(new ErrorMessage("Извините, что-то пошло не так, попробуйте снова!"));
//    }

    // === Для обработки ошибок Validation
    @ExceptionHandler(MethodArgumentNotValidException.class) //WebExchangeBindException.class
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(fe -> fe.getDefaultMessage()).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

}

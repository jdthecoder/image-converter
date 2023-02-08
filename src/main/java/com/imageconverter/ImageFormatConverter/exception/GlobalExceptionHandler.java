package com.imageconverter.ImageFormatConverter.exception;

import com.imageconverter.ImageFormatConverter.model.ErrorObject;
import com.imageconverter.ImageFormatConverter.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FormatConverterException.class)
    public ResponseEntity<ErrorResponse> handleFormatConverterException(FormatConverterException ex, HttpServletRequest request) {
        log.error("Exception trace ==> ", ex);
        ErrorResponse errorResponse = new ErrorResponse();

        String code;
        if(HttpStatus.valueOf(ex.getResponseCode()).value() == 400) {
            errorResponse.setErrors(ErrorObject.builder()
                    .code("error.validation")
                    .field("file")
                    .message("Image conversion failed").build());
        } else {
            errorResponse.setErrors(ErrorObject.builder()
                    .code("error.internalServerError")
                    .field(null)
                    .message(ex.getMessage()).build());
        }
        log.error("Error response : {}", errorResponse);
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getResponseCode()));
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ErrorResponse> handleInvalidInputException(InvalidInputException ex, HttpServletRequest request) {
        log.error("Exception trace ==> ", ex);
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setErrors(ErrorObject.builder()
                .code("error.validation")
                .field("file")
                .message(ex.getMessage()).build());

        log.error("Error response : {}", errorResponse);
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getResponseCode()));
    }
}

package com.imageconverter.ImageFormatConverter.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InvalidInputException extends RuntimeException {

    private int responseCode;

    public InvalidInputException(String message, int responseCode) {
        super(message);
        this.responseCode = responseCode;
    }
}

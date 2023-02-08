package com.imageconverter.ImageFormatConverter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorObject {
    private String code;
    private String field;
    private String message;
}

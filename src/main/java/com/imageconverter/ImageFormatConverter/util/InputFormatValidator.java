package com.imageconverter.ImageFormatConverter.util;

import org.springframework.web.multipart.MultipartFile;

public class InputFormatValidator {

    public static boolean validateJPG(MultipartFile file) {
        if(file.getContentType().trim().equalsIgnoreCase("image/jpeg"))
            return true;
        return false;
    }

    public static boolean validatePNG(MultipartFile file) {
        if(file.getContentType().trim().equalsIgnoreCase("image/png"))
            return true;
        return false;
    }
}

package com.imageconverter.ImageFormatConverter.service;

import com.imageconverter.ImageFormatConverter.service.impl.FormatImageImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

public class FormatImageTest {

    private FormatImage formatImage;

    @BeforeEach
    void setUp() {
        formatImage = new FormatImageImpl();
    }

    @Test
    void fileShouldBeConvertedFromJpgToPng() {
        File file =  new File("src/test/resources/images/jpgimage.jpg");
        file = formatImage.convertToPng(file);
        System.out.println(file.getName());
    }
}

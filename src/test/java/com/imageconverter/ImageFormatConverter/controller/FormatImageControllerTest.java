package com.imageconverter.ImageFormatConverter.controller;

import com.imageconverter.ImageFormatConverter.service.FormatImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.text.Normalizer;

@WebMvcTest(FormatImageController.class)
public class FormatImageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FormatImage formatImage;

    @Test
    public void convertJpgToPngTest() {

    }
}

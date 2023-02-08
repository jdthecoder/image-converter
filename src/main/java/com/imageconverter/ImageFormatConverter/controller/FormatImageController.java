package com.imageconverter.ImageFormatConverter.controller;

import com.imageconverter.ImageFormatConverter.exception.FormatConverterException;
import com.imageconverter.ImageFormatConverter.service.FormatImage;
import com.imageconverter.ImageFormatConverter.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/convert")
public class FormatImageController {

    @Autowired
    FormatImage formatImage;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String home() {
        return "Welcome";
    }

    @PostMapping(value = "/toPng", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public byte[] convertToPng(@RequestParam("fileToConvert") MultipartFile fileToConvert) {

        log.info("Entering PNG converter");
        String name = fileToConvert.getOriginalFilename();

        try {
            byte[] image = fileToConvert.getBytes();
            File file = formatImage.convertToPng(FileUtils.toFile(image, name));
            byte[] outputData = FileUtils.toByteArray(file);
            FileUtils.removeFilesFromTemp(name, file.getName());

            return outputData;
        } catch (IOException e) {
            throw new FormatConverterException(e.getMessage(), 500);
        }

    }

    @PostMapping(value = "/toJpg", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] convertToJpg(@RequestParam("fileToConvert") MultipartFile fileToConvert) {
        log.info("Entering JPG converter");
        String name = fileToConvert.getOriginalFilename();

        try {
            byte[] image = fileToConvert.getBytes();
            File file = formatImage.convertToJpg(FileUtils.toFile(image, name));
            byte[] outputData = FileUtils.toByteArray(file);
            FileUtils.removeFilesFromTemp(name, file.getName());

            return outputData;
        } catch (IOException e) {
            e.printStackTrace();
            throw new FormatConverterException("File parsing failed", 500);
        }

    }

    @PostMapping("/toPdf")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public void convertToPdf(@RequestParam("fileToConvert") MultipartFile fileToConvert) {
        log.info("Entering PDF converter"); //for getting the number of requests
    }

    @PostMapping(value = "/toBmp", produces = "image/bmp")
    public byte[] convertToBmp(@RequestParam("fileToConvert") MultipartFile fileToConvert) {
        log.info("Entering BMP converter");
        String name = fileToConvert.getOriginalFilename();

        try {
            byte[] image = fileToConvert.getBytes();
            File file = formatImage.convertToBmp(FileUtils.toFile(image, name));
            byte[] outputData = FileUtils.toByteArray(file);
            FileUtils.removeFilesFromTemp(name, file.getName());

            return outputData;
        } catch (IOException e) {
            e.printStackTrace();
            throw new FormatConverterException("File parsing failed", 500);
        }
    }

}
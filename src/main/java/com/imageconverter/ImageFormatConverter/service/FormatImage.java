package com.imageconverter.ImageFormatConverter.service;

import java.io.File;

public interface FormatImage {

    //Add different image file formats for conversion

    public File convertToPng(File file);

    public File convertToJpg(File file);

    public File convertToPdf(File file);

    public File convertToBmp(File toFile);
}

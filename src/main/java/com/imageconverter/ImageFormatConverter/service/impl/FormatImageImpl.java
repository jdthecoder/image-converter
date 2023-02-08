package com.imageconverter.ImageFormatConverter.service.impl;

import com.imageconverter.ImageFormatConverter.exception.FormatConverterException;
import com.imageconverter.ImageFormatConverter.service.FormatImage;
import com.imageconverter.ImageFormatConverter.util.Constants;
import com.imageconverter.ImageFormatConverter.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.imaging.*;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Slf4j
@Service
public class FormatImageImpl implements FormatImage {

    @Override
    public File convertToPng(File file) {
        File outputFile = convertFileFormat(file, ImageFormats.PNG);
        return outputFile;
    }

    @Override
    public File convertToJpg(File file) {
        File outputFile = convertFileFormat(file, ImageFormats.JPEG);
        return outputFile;
    }

    @Override
    public File convertToPdf(File file) {
        return null;
    }

    public File convertToBmp(File file) {
        File outputFile = convertFileFormat(file, ImageFormats.BMP);
        return outputFile;
    }

    private File convertFileFormat(File file, ImageFormats newFormat) {
        File outputFile = file;
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = Imaging.getBufferedImage(file);
            outputFile = new File(Constants.PATH_TO_TEMP_STORAGE + FileUtils.getNewFileName(file.getName(), newFormat));
            Imaging.writeImage(bufferedImage, outputFile, newFormat);
        } catch (ImageWriteException ex) {
            try {
                ImageIO.write(bufferedImage, "JPEG", outputFile);
            } catch (IOException e) {
                log.error("Error writing JPEG file: " + e);
            }
        }
        catch (ImageReadException | IOException e) {
            throw new FormatConverterException(e.getMessage(), 500);
        }
        return outputFile;
    }
}

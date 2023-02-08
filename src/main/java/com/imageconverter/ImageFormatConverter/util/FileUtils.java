package com.imageconverter.ImageFormatConverter.util;

import org.apache.commons.imaging.ImageFormats;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {

    public static File toFile(byte[] data, String fileName) throws IOException {
        File file = new File(Constants.PATH_TO_TEMP_STORAGE + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(data);
        fos.close();
        return file;
    }

    public static byte[] toByteArray(File file) throws IOException {
        byte[] data = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        fis.read(data);
        fis.close();
        return data;
    }

    public static String getNewFileName(String name, ImageFormats imageFormats) {
        StringBuilder stringBuilder = new StringBuilder(name);
        stringBuilder.delete(stringBuilder.lastIndexOf("."), name.length());
        stringBuilder.append(".").append(imageFormats.getName());
        return stringBuilder.toString();
    }

    public static void clearTempFolder() throws IOException {
        org.apache.tomcat.util.http.fileupload.FileUtils.cleanDirectory(new File(Constants.PATH_TO_TEMP_STORAGE));
    }

    public static void removeFilesFromTemp(String pathToFile1, String pathToFile2) throws IOException {
        org.apache.tomcat.util.http.fileupload.FileUtils.forceDelete(new File(Constants.PATH_TO_TEMP_STORAGE + pathToFile1));
        org.apache.tomcat.util.http.fileupload.FileUtils.forceDelete(new File(Constants.PATH_TO_TEMP_STORAGE + pathToFile2));
    }
}

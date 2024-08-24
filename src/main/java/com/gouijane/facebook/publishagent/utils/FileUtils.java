package com.gouijane.facebook.publishagent.utils;

import com.gouijane.facebook.publishagent.exception.FileUnzippingException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.gouijane.facebook.publishagent.utils.contants.ExceptionMessages.COULD_NOT_UNZIP_FILE;


public class FileUtils {
    private static final String IMAGE_DESTINATION_DIRECTORY = "src/main/resources/images";

    public void unzip(MultipartFile images) throws FileUnzippingException {
        File dir = new File(IMAGE_DESTINATION_DIRECTORY);
        if (!dir.exists()) dir.mkdirs();

        try (InputStream inputStream = images.getInputStream();
             ZipInputStream zis = new ZipInputStream(inputStream)) {
            ZipEntry ze = zis.getNextEntry();
            while (ze != null) {
                String fileName = ze.getName();
                File newFile = new File(IMAGE_DESTINATION_DIRECTORY + File.separator + fileName);
                // Create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                try (FileOutputStream fos = new FileOutputStream(newFile)) {
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                }
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
        } catch (IOException e) {
            throw new FileUnzippingException(COULD_NOT_UNZIP_FILE);
        }
    }
    public void cleanUpImageDirectory() throws IOException {
        File directory = new ClassPathResource("images").getFile();
        if (directory.exists()) {
            deleteDirectory(directory);
        }
    }
    private void deleteDirectory(File directory) {
        // Recursively delete all files and subdirectories
        File[] allContents = directory.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
       directory.delete();
    }
}

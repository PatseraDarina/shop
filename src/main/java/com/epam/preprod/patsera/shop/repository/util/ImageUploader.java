package com.epam.preprod.patsera.shop.repository.util;

import com.epam.preprod.patsera.shop.exception.SavingImageException;
import com.epam.preprod.patsera.shop.util.Constant;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageUploader {
    private final Part part;
    private String email;

    public ImageUploader(Part part, String email) {
        this.part = part;
        this.email = email;
    }

    public void saveImage() throws SavingImageException, IOException {
        String path = getPath();
        writeImageOnDisk(part, path);
    }

    private String getPath() throws IOException {
        Path path = Paths.get(Constant.PATH_AVATARS);
        String extension = getImageExtension();
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        return path.toString() + File.separatorChar + email + extension;
    }

    private String getImageExtension() {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return "." + FilenameUtils.getExtension(s.substring(s.indexOf("=") + 2, s.length() - 1));
            }
        }
        return Constant.EMPTY;
    }

    private void writeImageOnDisk(Part part, String fullImageName) throws SavingImageException {
        try {
            part.write(fullImageName);
        } catch (IOException e) {
            throw new SavingImageException(Constant.Error.FAIL_IMAGE + fullImageName, e);
        }
    }
}

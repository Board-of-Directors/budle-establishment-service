package ru.nsu.fit.directors.establishmentservice.service;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.nsu.fit.directors.establishmentservice.exception.ImageSavingException;

import javax.annotation.Nullable;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.Iterator;

@Component
public class ImageWorker {
    private final static String SERVER_PATH = "./images";
    private final static String IMAGE_PATH_PREFIX = SERVER_PATH + '/';
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public String saveImage(String imageContent) {
        String databaseFilepath = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) + ".jpg";
        String saveFilepath = IMAGE_PATH_PREFIX + databaseFilepath;
        try {
            File file = new File(saveFilepath);
            byte[] imageBytes = Base64.getDecoder().decode(imageContent);
            FileUtils.writeByteArrayToFile(file, imageBytes);
            compressImage(saveFilepath);
            return databaseFilepath;
        } catch (Exception e) {
            logger.warn(e.getMessage());
            throw new ImageSavingException();

        }
    }

    public void compressImage(String filepath) {
        try {
            File input = new File(filepath);
            BufferedImage image = ImageIO.read(input);

            File compressedImageFile = new File(filepath);
            OutputStream outputStream = new FileOutputStream(compressedImageFile);

            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpeg");
            ImageWriter writer = writers.next();

            ImageWriteParam param = writer.getDefaultWriteParam();

            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(0.7f);

            ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
            writer.setOutput(imageOutputStream);

            writer.write(null, new IIOImage(image, null, null), param);

            outputStream.close();
            imageOutputStream.close();
            writer.dispose();
        } catch (IOException exception) {
            logger.warn(exception.getMessage());
            throw new ImageSavingException();
        }

    }

    @Nullable
    public String loadImage(String imageName) {
        try {
            File inputFile = new File(IMAGE_PATH_PREFIX + imageName);
            byte[] fileContent = FileUtils.readFileToByteArray(inputFile);
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            return null;
        }
    }

    @Nullable
    public String getImageFromResource(String imageName) {
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream(SERVER_PATH + imageName)) {
            if (stream != null) {
                StringBuilder builder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                while (reader.ready()) {
                    builder.append(reader.readLine());
                }
                return builder.toString();
            }
        } catch (IOException e) {
            logger.warn(e.getMessage());
        }
        return null;
    }
}

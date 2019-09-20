package com.util;

import com.payload.ResizedImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageResizeUtil {

    public static ResizedImage resize(byte[] fileData) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(fileData);
        ResizedImage resizedImage = new ResizedImage();
        try {
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            resizedImage.setHeight(bufferedImage.getHeight());
            resizedImage.setWidth(bufferedImage.getWidth());

            Image scaledImage = bufferedImage.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
            BufferedImage imageBuff = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
            imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0,0,0), null);

            ImageIO.write(imageBuff, "jpg", buffer);

            resizedImage.setData(buffer.toByteArray());

            return resizedImage;
        } catch (IOException e) {
            throw new IOException("Resizing occurred");
        }
    }

    private ImageResizeUtil() {
    }
}

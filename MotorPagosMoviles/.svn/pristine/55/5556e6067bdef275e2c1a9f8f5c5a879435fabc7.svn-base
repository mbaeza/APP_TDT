package cl.bch.motorpagos.util;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public final class ImageUtils {
	private static final Logger logger = LoggerFactory.getLogger(ImageUtils.class);
	
	/**
	 * 
	 */
	private ImageUtils(){
		
	}
    /**
     * Decode string to image
     * @param imageString The string to decode
     * @return decoded image
     */
    public static BufferedImage decodeToImage(String imageString) {

        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (IOException e) {
            logger.error("Se produjo un error al crear la imagen.", e);
        }
        return image;
    }

    /**
     * Encode image to string
     * @param image The image to encode
     * @param type jpeg, bmp, ...
     * @return encoded string
     */
    public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
        	logger.error("Se produjo un error al leer la imagen.", e);
        }
        return imageString;
    }

//    public static void main (String args[]) throws IOException {
//        /* Test image to string and string to image start */
//        BufferedImage img = ImageIO.read(new File("C:/TestImage.png"));
//        BufferedImage newImg;
//        String imgstr;
//        imgstr = encodeToString(img, "png");
//        System.out.println(imgstr);
//        newImg = decodeToImage(imgstr);
//        ImageIO.write(newImg, "png", new File("C:/CopyOfTestImage.png"));
//        /* Test image to string and string to image finish */
//    }
}




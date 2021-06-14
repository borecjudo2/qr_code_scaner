package by.judoka.qr.zxing;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import lombok.SneakyThrows;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;

/**
 * Created by
 *
 * @author: JUDOKA
 * Date: 6/6/2021
 */


public class QrCodeZxing {

    public static Optional<String> readQR(byte[] bytes) {
        try {
            BinaryBitmap binaryBitmap = toBitmap(bytes);
            Result result = new MultiFormatReader().decode(binaryBitmap);
            return Optional.of(result.getText());
        } catch (NotFoundException e) {
            return Optional.empty();
        }
    }

    @SneakyThrows
    private static BinaryBitmap toBitmap(byte[] bytes){
        InputStream inputStream = new ByteArrayInputStream(bytes);
        BufferedImage image = ImageIO.read(inputStream);
        BufferedImageLuminanceSource bufferedImageLuminanceSource = new BufferedImageLuminanceSource(image);
        HybridBinarizer hybridBinarizer = new HybridBinarizer(bufferedImageLuminanceSource);
        return new BinaryBitmap(hybridBinarizer);
    }

}
package by.judoka.qr.zxing;

import by.judoka.qr.Image;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import lombok.SneakyThrows;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.ByteArrayInputStream;

/**
 * Created by
 *
 * @author: JUDOKA
 * Date: 6/6/2021
 */

@Named
@RequestScoped
public class QrCodeZxing {

    @SneakyThrows
    public void readQR(Image image) {
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(ImageIO.read(new ByteArrayInputStream(image.getData())))));
        Result result = new MultiFormatReader().decode(binaryBitmap);
        FacesMessage message = new FacesMessage("Successful", result.getText());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
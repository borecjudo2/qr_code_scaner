package by.judoka.qr;

import by.judoka.qr.image.Image;
import by.judoka.qr.zxing.QrCodeZxing;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Optional;

@Getter
@Setter
@Named
@RequestScoped
public class Analyzer implements Serializable {

    @Inject
    private Image image;

    private boolean isAnalyzed = false;

    public void analyze(){
        isAnalyzed = true;
    }

    public Optional<String> getDataFromStandardQrCode(){
        return QrCodeZxing.readQR(image.getData());
    }

    public Optional<String> getDataFromRotateQrCode(){
        return QrCodeZxing.readQR(image.getDataRotate());
    }

    public Optional<String> getDataFromGaussianQrCode(){
        return QrCodeZxing.readQR(image.getDataGaussian());
    }

    public Optional<String> getDataFromScaleQrCode(){
        return QrCodeZxing.readQR(image.getDataPlus());
    }

    public Optional<String> getDataFromBarrelInverseQrCode(){
        return QrCodeZxing.readQR(image.getDataBarrelInverse());
    }

    public Optional<String> getDataFromBarrelQrCode(){
        return QrCodeZxing.readQR(image.getDataBarrel());
    }

}

package by.judoka.qr.image;

import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.ByteArrayInputStream;
import java.io.Serializable;

/**
 * Created by
 *
 * @author: JUDOKA
 * Date: 6/7/2021
 */

@Getter
@Setter
@Named
@RequestScoped
public class PrintImage implements Serializable {

    @Inject
    private Image image;

    public boolean isHaveImage(){
        return !image.isEmpty();
    }

    public StreamedContent getImage() {
        return DefaultStreamedContent.builder()
                .name(image.getName())
                .contentType(image.getMimeType())
                .stream(() -> new ByteArrayInputStream(image.getData()))
                .build();
    }

    public StreamedContent getRotateImage() {
        return toImage(image.getDataRotate());
    }

    public StreamedContent getGaussianImage(){
        return toImage(image.getDataGaussian());
    }

    public StreamedContent getScaleImage(){
        return toImage(image.getDataPlus());
    }

    public StreamedContent getDistortionPincushionImage(){
        return toImage(image.getDataBarrelInverse());
    }

    public StreamedContent getDistortionBarrelImage(){
        return toImage(image.getDataBarrel());
    }

    private StreamedContent toImage(byte[] bytes){
        return DefaultStreamedContent.builder()
                .contentType(this.image.getMimeType())
                .stream(() -> new ByteArrayInputStream(bytes))
                .build();
    }

}
package by.judoka.qr.image;

import by.judoka.qr.Image;
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

    @Inject
    private ImageManipulation imageManipulation;

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

    public StreamedContent getRotateImage(int degree) {
        return imageManipulation.rotate(degree);
    }

    public StreamedContent getGauseImage(){
        return imageManipulation.getGause();
    }

    public StreamedContent getScaleImage(){
        return imageManipulation.scale();
    }


}
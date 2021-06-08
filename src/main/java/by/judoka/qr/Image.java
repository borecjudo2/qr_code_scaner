package by.judoka.qr;

import com.google.common.base.Strings;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.primefaces.model.file.UploadedFile;

import javax.faces.view.ViewScoped;
import javax.imageio.ImageIO;
import javax.inject.Named;
import javax.inject.Singleton;
import java.awt.image.BufferedImage;
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
@Singleton
public class Image implements Serializable {

    private String name;

    private String mimeType;

    private byte[] data;

    public void convertFromUploadFile(UploadedFile file){
        name = file.getFileName();
        mimeType = file.getContentType();
        data = file.getContent();
    }

    public boolean isEmpty(){
        return Strings.isNullOrEmpty(name) || Strings.isNullOrEmpty(mimeType) || data == null;
    }

    @SneakyThrows
    public BufferedImage toBufferedImage(){
        return ImageIO.read(new ByteArrayInputStream(data));
    }
    @SneakyThrows
    public BufferedImage toEmptyBufferedImage(){
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(data));
        return new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());
    }


}
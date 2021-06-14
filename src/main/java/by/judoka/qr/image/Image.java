package by.judoka.qr.image;

import com.google.common.base.Strings;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.primefaces.model.file.UploadedFile;

import javax.imageio.ImageIO;
import javax.inject.Named;
import javax.inject.Singleton;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Base64;

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

    private byte[] dataRotate;

    private byte[] dataGaussian;

    private byte[] dataPlus;

    private byte[] dataBarrel;

    private byte[] dataBarrelInverse;

    @SneakyThrows
    public void convertFromUploadFile(UploadedFile file){
        name = file.getFileName();
        mimeType = file.getContentType();
        data = initData(file);
       initAdditionData();
    }

    @SneakyThrows
    private byte[] initData(UploadedFile file){
        BufferedImage image = resize(ImageIO.read(new ByteArrayInputStream(file.getContent())));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);
        return outputStream.toByteArray();
    }

    private void initAdditionData(){
        ImageConverter imageConverter = new ImageConverter(this);
        dataRotate = imageConverter.convertToRotate(90);
        dataGaussian = imageConverter.convertToGaussian();
        dataPlus = imageConverter.convertToPlus();
        dataBarrel = imageConverter.convertToBarrel();
        dataBarrelInverse = imageConverter.convertToBarrelInverse();
    }

    private BufferedImage resize(BufferedImage img) {
        java.awt.Image tmp = img.getScaledInstance(350, 350, java.awt.Image.SCALE_SMOOTH);
        BufferedImage bufferedImage = new BufferedImage(350, 350, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = bufferedImage.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return bufferedImage;
    }

    public String getDataBase64(){
        return Base64.getEncoder().encodeToString(data);
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
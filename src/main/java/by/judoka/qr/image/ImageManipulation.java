package by.judoka.qr.image;

import by.judoka.qr.Image;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import javax.enterprise.context.RequestScoped;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.stream.IntStream;

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
public class ImageManipulation {

    @Inject
    private Image image;

    public StreamedContent rotate(double degree){
        BufferedImage newImage = image.toEmptyBufferedImage();
        AffineTransform affineTransform = AffineTransform.getRotateInstance(Math.toRadians(degree), newImage.getWidth()/2.0, newImage.getHeight()/2.0);
        BufferedImageOp op = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BILINEAR);
        op.filter(image.toBufferedImage(), newImage);
        return getReturn(newImage);
    }

    @SneakyThrows
    private StreamedContent getReturn(BufferedImage image){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        byte[] bytes = baos.toByteArray();
        return DefaultStreamedContent.builder()
                .contentType(this.image.getMimeType())
                .stream(() -> new ByteArrayInputStream(bytes))
                .build();
    }

    public StreamedContent getGause(){
        int[] filter = {1, 2, 1, 2, 4, 2, 1, 2, 1,1, 2, 1, 2, 4, 2, 1, 2, 1};
        int filterWidth = 18;
        return getReturn(blur(image.toBufferedImage(), filter, filterWidth));
    }

    public BufferedImage blur(BufferedImage image, int[] filter, int filterWidth) {
        if (filter.length % filterWidth != 0) {
            throw new IllegalArgumentException("filter contains a incomplete row");
        }

        final int width = image.getWidth();
        final int height = image.getHeight();
        final int sum = IntStream.of(filter).sum();

        int[] input = image.getRGB(0, 0, width, height, null, 0, width);

        int[] output = new int[input.length];

        final int pixelIndexOffset = width - filterWidth;
        final int centerOffsetX = filterWidth / 2;
        final int centerOffsetY = filter.length / filterWidth / 2;

        // apply filter
        for (int h = height - filter.length / filterWidth + 1, w = width - filterWidth + 1, y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int r = 0;
                int g = 0;
                int b = 0;
                for (int filterIndex = 0, pixelIndex = y * width + x;
                     filterIndex < filter.length;
                     pixelIndex += pixelIndexOffset) {
                    for (int fx = 0; fx < filterWidth; fx++, pixelIndex++, filterIndex++) {
                        int col = input[pixelIndex];
                        int factor = filter[filterIndex];

                        // sum up color channels seperately
                        r += ((col >>> 16) & 0xFF) * factor;
                        g += ((col >>> 8) & 0xFF) * factor;
                        b += (col & 0xFF) * factor;
                    }
                }
                r /= sum;
                g /= sum;
                b /= sum;
                // combine channels with full opacity
                output[x + centerOffsetX + (y + centerOffsetY) * width] = (r << 16) | (g << 8) | b | 0xFF000000;
            }
        }

        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        result.setRGB(0, 0, width, height, output, 0, width);
        return result;
    }

    public StreamedContent scale(){
        BufferedImage before = image.toBufferedImage();
        BufferedImage after = new BufferedImage(before.getWidth(), before.getHeight(), BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = AffineTransform.getScaleInstance(1.2, 1.2);

        AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        after = scaleOp.filter(before, after);
        return getReturn(after);
    }

}
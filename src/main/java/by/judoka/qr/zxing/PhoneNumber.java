package by.judoka.qr.zxing;

/**
 * Created by
 *
 * @author: JUDOKA
 * Date: 6/7/2021
 */


public class PhoneNumber {

    private String codeCountry;

    private String phone;

    @Override
    public String toString() {
        return "+("+codeCountry+")"+phone;
    }
}
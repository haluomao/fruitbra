package com.money.service;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * [Description]
 * date: 2016/7/31
 *
 * @author maofagui
 * @version 1.0
 */
public class RCodeServiceImpl {
    public static void main(String []args){
        RCodeServiceImpl pro  = new RCodeServiceImpl();
        pro.encode("120605181003;http://www.cnblogs.com/jtmjx", "E:\\cache\\122.jpg");
    }

    public static void encode(String content, String filePath){
        try {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            Map hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 180, 180,hints);
            File file = new File(filePath);
            MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Result decode(String filePath){
        try {
            MultiFormatReader formatReader = new MultiFormatReader();
            File file = new File(filePath);
            BufferedImage image = ImageIO.read(file);;
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer  binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            Result result = formatReader.decode(binaryBitmap,hints);

            System.out.println("result = "+ result.toString());
            System.out.println("resultFormat = "+ result.getBarcodeFormat());
            System.out.println("resultText = "+ result.getText());

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

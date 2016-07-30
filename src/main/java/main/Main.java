package main;

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
 * Created by mfg on 16/07/30.
 */
public class Main {

    public static void main(String[] args) {
        Main pro = new Main();
        pro.encode();
        pro.decode();
    }
    public void encode(){
        try {

            String content = "120605181003;http://www.cnblogs.com/jtmjx";
            String path = "E:\\cache";

            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

            Map hints = new HashMap();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400,hints);
            File file1 = new File(path,"123.jpg");
            MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void decode(){
        try {
            MultiFormatReader formatReader = new MultiFormatReader();
            String filePath = "E:\\cache/123.jpg";
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

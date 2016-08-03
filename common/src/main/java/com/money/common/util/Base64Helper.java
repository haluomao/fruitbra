package com.money.common.util;


import sun.nio.cs.ext.IBM33722;

import java.io.*;
import java.util.Base64;

/**
 * [Description]
 * date: 2016/8/2
 *
 * @author maofagui
 * @version 1.0
 */
public class Base64Helper {
    /**
     * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     *
     * @param imgFilePath
     * @return
     */
    public static String genImageStr(String imgFilePath) {
        byte[] data = null;

        // 读取图片字节数组
        InputStream in = null;
        try {
            in = new FileInputStream(imgFilePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(in!=null)
                    in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 对字节数组Base64编码
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(data);
    }

    /**
     * 对字节数组字符串进行Base64解码并生成图片
     * @param imgStr
     * @param imgFilePath
     * @return
     */
    public static boolean genImageFromBase64(String imgStr, String imgFilePath) {
        if (imgStr == null)
            return false;
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            // Base64解码
            byte[] bytes = decoder.decode(imgStr);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }

            // 生成jpeg图片
            File file = new File(imgFilePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            OutputStream out = new FileOutputStream(file);
            out.write(bytes);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

package com.lc.demoapp.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;


import javax.imageio.ImageIO;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码编码&解码
 *
 * @Author: lc 2017-11-9 10:32:53
 * @Version:1
 * @Desc: <p>带logo 的 二维码 zxing 版本</p>
 */
public class QRCodeUtilWithLogo {
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    /*
     * 解码：
     * 1 将图片反解码为二维矩阵
     * 2 将该二维矩阵解码为内容
     * */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void decode(String imgPath) {
        try {
            File file = new File(imgPath);//获取该图片文件  
            BufferedImage image;
            try {
                image = ImageIO.read(file);
                if (image == null) {
                    System.out.println("Could not decode image");
                }
                LuminanceSource source = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                Result result;
                Hashtable hints = new Hashtable();//将图片反解码为二维矩阵  
                hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
                result = new MultiFormatReader().decode(bitmap, hints);//将该二维矩阵解码成内容  
                String resultStr = result.getText();
                System.out.println("解码结果：" + resultStr);

            } catch (IOException ioe) {
                System.out.println(ioe.toString());
            } catch (ReaderException re) {
                System.out.println(re.toString());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 编码
     *
     * @param contents
     * @param width
     * @param height
     * @param imgPath
     * @param logoPath
     * @return
     * @author
     * @data
     */
    public static boolean encode(String contents, int width, int height, String imgPath, String logoPath) {
        Map<EncodeHintType, Object> hints = new Hashtable<>();
        // 指定纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 指定编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //设置二维码内容到边框的距离
        hints.put(EncodeHintType.MARGIN, 1);
        String format = "png";

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);

            MatrixToImageWriter.writeToStream(bitMatrix, format, new FileOutputStream(imgPath));

            File qrcodeFile = new File(imgPath);

            //增加logo
            writeToLogo(bitMatrix, format, qrcodeFile, logoPath);

        } catch (Exception e) {
            return false;
        }
        System.out.println("编码成功！");
        return true;
    }

    /**
     * 增加Logo
     *
     * @param matrix
     * @param format
     * @param file
     * @param logoPath
     * @throws IOException
     * @author
     * @data
     */
    public static void writeToLogo(BitMatrix matrix, String format, File file, String logoPath) throws IOException {
        Graphics2D graphics2 = null;
        BufferedImage image = null;
        BufferedImage logo = null;
        try {
            /**
             * 读取二维码图片，并构建绘图对象 
             */
            image = toBufferedImage(matrix);
            graphics2 = image.createGraphics();

            /**
             * 读取Logo图片 
             */
            logo = ImageIO.read(new File(logoPath));
            int codeWidth = image.getWidth();
            int codeHeight = image.getHeight();
            /**
             * 设置logo的大小,设置为二维码图片的25%,因为过大会盖掉二维码 
             */
            int widthLogo = logo.getWidth(null) > codeWidth * 2 / 13 ? (codeWidth * 2 / 13) : logo.getWidth(null),
                    heightLogo = logo.getHeight(null) > codeHeight * 2 / 13 ? (codeHeight * 2 / 13) : logo.getWidth(null);

            /**
             * 计算图片放置位置  
             * logo放在中心 
             */
            int x = (codeWidth - widthLogo) / 2;
            int y = (codeHeight - heightLogo) / 2;
            int radius = 14;//圆角范围

            //填充与logo大小类似的扁平化圆角矩形背景
            graphics2.setComposite(AlphaComposite.Src);
            graphics2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2.setColor(Color.WHITE);
            graphics2.fill(new RoundRectangle2D.Float(x - 2, y - 2, widthLogo + 4, heightLogo + 4, radius, radius));
            graphics2.setComposite(AlphaComposite.SrcAtop);

            //开始绘制logo图片  
            graphics2.drawImage(logo, x, y, widthLogo, heightLogo, null);

            if (!ImageIO.write(image, format, file)) {
                throw new IOException("Could not write an image of format " + format + " to " + file);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (graphics2 != null) {
                graphics2.dispose();
            }
            if (logo != null) {
                logo.flush();
            }
            if (image != null) {
                image.flush();
            }
        }
    }

    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    /*
     *
     * // 生成二维码 File outputFile = new File("f:" +
     * File.separator+"fwzsxtKu"+File.
     * separator+File.separator+"Ku1"+File.separator+ "zxing.png");
     *
     * if(outputFile.exists()){ System.out.println("目录：我在！"); }else {
     * System.out.println("目录：我不在，我建个！"); outputFile.mkdirs(); }
     *
     * System.out.println(outputFile); System.out.println(imgPath2);
     *
     * String imgPath=outputFile.toString();
     *
     * -------------------------------
     *  目录：我在！ f:\fwzsxtKu\Ku1\zxing.png
     * F:/zxing/zxing.png javax.imageio.IIOException: Can't create an
     * ImageInputStream! ok
     */

    public static void main(String[] args) {
        String contents = "http://oneplusone.vip";
        String imgPath2 = "D:/#QrCodeKu/zxing_QRCodeUtilWithLogo.png";

        System.out.println(imgPath2);

        String logoPath = "D:/LCLogo.jpg";
        QRCodeUtilWithLogo.encode(contents, 200, 200, imgPath2, logoPath);

        QRCodeUtilWithLogo.decode(imgPath2);
        System.out.println("ok");
    }
}
package com.lc.demoapp.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class CreateMD5 {  
	  
    //静态方法，便于作为工具类  
    public static String getMd5(String plainText) {  
        try {  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            md.update(plainText.getBytes());  
            byte b[] = md.digest();  
  
            int i;  
  
            StringBuffer buf = new StringBuffer("");  
            for (int offset = 0; offset < b.length; offset++) {  
                i = b[offset];  
                if (i < 0)  
                    i += 256;  
                if (i < 16)  
                    buf.append("0");  
                buf.append(Integer.toHexString(i));  
            }  
            //32位加密  
            return buf.toString();  
            // 16位的加密  
            //return buf.toString().substring(8, 24);  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
            return null;  
        }  
  
    }


   /*MD5Util */
    public static String encryptMd5(String string) throws UnsupportedEncodingException {
        return encryptMd5(string, "UTF-8");
    }


    public static String encryptMd5(String string, String charSet) throws UnsupportedEncodingException {
        return DigestUtils.md5Hex(string.getBytes(charSet));
    }


    /**
     * 计算字符串的MD5值
     * @param  signStr:签名字符串
     * @return
     */
    public static String getSign(String signStr) {
        try{
            String md5 = encryptMd5(signStr);
            return md5;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return  null ;
        }
    }



    public static void main(String args[]) throws  Exception{
        //md5(admin,32) = 21232f297a57a5a743894a0e4a801fc3
        System.out.println(encryptMd5("admin"));//21232f297a57a5a743894a0e4a801fc3
    }

}

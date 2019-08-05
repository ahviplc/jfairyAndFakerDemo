package com.lc.demoapp.utils;


import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class AESUtil {
	public static boolean initialized = false;
	
	public static final String ALGORITHM = "AES/ECB/PKCS7Padding";
	
	//public static final byte[] passArr=new byte[]{0x00,0x03,0x15,0x00,0x01,0x10,0x05,0x46,0x4c,0x4f,0x57,0x4d,0x45,0x54,0x45,0x4b};
	/**
	 * @param  String str  要被加密的字符串
	 * @param  byte[] key  加/解密要用的长度为32的字节数组（256位）密钥
	 * @return byte[]  加密后的字节数组
	 */
	public static byte[] Aes256Encode(String str, byte[] key){
		initialize();
		byte[] result = null;
		try{
			Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
			SecretKeySpec keySpec = new SecretKeySpec(key, "AES"); //生成加密解密需要的Key
			cipher.init(Cipher.ENCRYPT_MODE, keySpec);
			byte[] bytes=toBytes(str);
			byte[] crcBytes=new byte[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
			if (bytes.length<16) {
				crcBytes[0]=bytes[0];
				crcBytes[1]=bytes[1];
				crcBytes[2]=0x0e;
				crcBytes[3]=0x0e;
				crcBytes[4]=0x0e;
				crcBytes[5]=0x0e;
				crcBytes[6]=0x0e;
				crcBytes[7]=0x0e;
				crcBytes[8]=0x0e;
				crcBytes[9]=0x0e;
				crcBytes[10]=0x0e;
				crcBytes[11]=0x0e;
				crcBytes[12]=0x0e;
				crcBytes[13]=0x0e;
				crcBytes[14]=0x0e;
				crcBytes[15]=0x0e;
			}
			result = cipher.doFinal(crcBytes);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @param  byte[] bytes  要被解密的字节数组
	 * @param  byte[] key    加/解密要用的长度为32的字节数组（256位）密钥
	 * @return String  解密后的字符串
	 */
	public static byte[] Aes256Decode(byte[] bytes, byte[] key){
		initialize();
		try{
			Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
			SecretKeySpec keySpec = new SecretKeySpec(key, "AES"); //生成加密解密需要的Key
			cipher.init(Cipher.DECRYPT_MODE, keySpec);
			byte[] decoded = cipher.doFinal(bytes);
			return decoded;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static void initialize(){
		if (initialized) return;
		Security.addProvider(new BouncyCastleProvider());
		initialized = true;
	}
	
	/**
	 * @param  byte[] str  要被加密的字符串BYTE数组
	 * @param  byte[] key  加/解密要用的长度为32的字节数组（256位）密钥
	 * @return byte[]  加密后的字节数组
	 */
	public static byte[] Aes256Encode(byte[] str, byte[] key){
		initialize();
		byte[] result = null;
		try{
			Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
			SecretKeySpec keySpec = new SecretKeySpec(key, "AES"); //生成加密解密需要的Key
			cipher.init(Cipher.ENCRYPT_MODE, keySpec);
			result = cipher.doFinal(str);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	

	/**
     * 将16进制字符串转换为byte[]
     * 
     * @param str
     * @return
     */
    public static  byte[] toBytes(String str) {
        if(str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for(int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            	bytes[i] = (byte) Integer.parseInt(subStr, 16);
            
        }
        return bytes;
    }

}

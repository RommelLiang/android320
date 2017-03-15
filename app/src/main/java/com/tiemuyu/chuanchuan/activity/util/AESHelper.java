package com.tiemuyu.chuanchuan.activity.util;

import android.annotation.SuppressLint;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import Decoder.BASE64Encoder;


/**
 * @ClassName: AESHelper
 * @Description: AES工具类
 * @author hw
 * @date 2015-6-30
 */
public class AESHelper {

	/**
	 * 加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 * @throws InvalidAlgorithmParameterException
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressLint("TrulyRandom") 
	public static byte[] encrypt(String content, String password)
			throws InvalidAlgorithmParameterException, UnsupportedEncodingException {
		try {
			//String password1=str2HexStr(password);
			
			//System.out.println("---->password:"+password.length()+",内容"+password);
			SecretKeySpec key = new SecretKeySpec(password.getBytes("utf-8"), "AES");
			IvParameterSpec spec = new IvParameterSpec(password.getBytes("utf-8"));
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
			//cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(java.util.Base64.Decoder.decode(IV.getBytes("UTF-8"))));
			cipher.init(Cipher.ENCRYPT_MODE, key, spec);// 初始化

			byte[] dataBytes = content.getBytes();
			byte[] result = cipher.doFinal(dataBytes);

			// System.out.println("----base64前字符串-->"+new
			// String(byteContent,"utf-8"));
//			System.out.println("----base64前16进制字符串-->" + byte2HexStr(result)
//					+ ",长度1:" + result.length);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		// catch (NoSuchProviderException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return null;
	}

	/**   
	 * 字符串转换成十六进制字符串  
	 *  String str 待转换的ASCII字符串
	 * @return String 每个Byte之间空格分隔，如: [61 6C 6B]  
	 */      
	public static String str2HexStr(String str)    
	{      
	  
	    char[] chars = "0123456789ABCDEF".toCharArray();      
	    StringBuilder sb = new StringBuilder("");    
	    byte[] bs = str.getBytes();      
	    int bit;      
	        
	    for (int i = 0; i < bs.length; i++)    
	    {      
	        bit = (bs[i] & 0x0f0) >> 4;      
	        sb.append(chars[bit]);      
	        bit = bs[i] & 0x0f;      
	        sb.append(chars[bit]);    
	       // sb.append(' ');    
	    }      
	    return sb.toString().trim();      
	} 
	
	
	
	public static String encrypt1(String data, String password) {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
			int blockSize = cipher.getBlockSize();

			byte[] dataBytes = data.getBytes();
			int plaintextLength = dataBytes.length;
			if (plaintextLength % blockSize != 0) {
				plaintextLength = plaintextLength
						+ (blockSize - (plaintextLength % blockSize));
			}

			byte[] plaintext = new byte[plaintextLength];
			System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

			SecretKeySpec keyspec = new SecretKeySpec(password.getBytes(),
					"AES");
			IvParameterSpec ivspec = new IvParameterSpec(password.getBytes());

			cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
			byte[] encrypted = cipher.doFinal(plaintext);

			return base64Encode(encrypted);

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;
	}

	/**
	 * bytes转换成十六进制字符串
	 */
	public static String byte2HexStr(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			// if (n<b.length-1) hs=hs+":";
		}
		return hs.toUpperCase();
	}

	/**
	 * 加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 * @throws InvalidAlgorithmParameterException
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("static-access")
	public static String getAesString(String content, String password)
			throws InvalidAlgorithmParameterException, UnsupportedEncodingException {

		Base64 base64 = new Base64();
		return new String(base64.encode(encrypt(content, password)));

	}

	/**
	 * base 64 encode
	 * 
	 * @param bytes
	 *            待编码的byte[]
	 * @return 编码后的base 64 code
	 */
	public static String base64Encode(byte[] bytes) {
		// return new BASE64Encoder().encode(bytes);
		return new BASE64Encoder().encodeBuffer(bytes);
	}

	// /**
	// * 对象转换成字串
	// */
	// public static String object2String(String content,String key) throws
	// Exception {
	// ByteArrayOutputStream baos = new ByteArrayOutputStream();
	// ObjectOutputStream oos = new ObjectOutputStream(baos);
	// oos.writeObject(content);
	// byte[] byteArray = baos.toByteArray();
	// byteArray = encrypt(content,key);
	// byte[] str = Base64.encode(byteArray, 0);
	// return str.toString();
	// }
	// /**
	// * 字串转换成对象
	// */
	// public static Object string2Object(String info) throws Exception {
	// byte[] byteArray = Base64.decode(info);
	// byteArray = encrypt(byteArray,key);
	// ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
	// ObjectInputStream ois = new ObjectInputStream(bais);
	// return ois.readObject();
	// }

	/**
	 * 解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @return
	 */
	public static byte[] decrypt(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			// kgen.init(128, new SecureRandom(password.getBytes()));
			kgen.init(128);
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/CBC");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * 加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	public static byte[] encrypt2(String content, String password) {
		try {
			// KeyGenerator kgen = KeyGenerator.getInstance("AES");
			// kgen.init(128, new SecureRandom(password.getBytes()));
			// SecretKey secretKey = kgen.generateKey();
			// byte[] enCodeFormat = secretKey.getEncoded();
			// SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			// Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			// byte[] byteContent = content.getBytes("utf-8");
			// cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			// byte[] result = cipher.doFinal(byteContent);
			// return result; // 加密

			SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/CBC/NoPadding");
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			// Cipher.
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

}

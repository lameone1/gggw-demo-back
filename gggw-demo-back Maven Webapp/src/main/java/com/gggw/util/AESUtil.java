/**
 * Project Name:gggw-demo-backend Maven Webapp
 * File Name:AESUtil.java
 * Package Name:com.gggw.util
 * Date:2016-6-25上午11:27:02
 * Copyright (c) 2016, 502269006@qq.com All Rights Reserved.
 *
*/

package com.gggw.util;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;

/**
 * ClassName:AESUtil <br/>
 * Function: AES加密. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016-6-25 上午11:27:02 <br/>
 * @author   cgw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class AESUtil {
	
	private static final String KEY_ALGORITHM = "AES";    
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static String AES_PRIVATE_KEY = "123456";
    
    private static Logger logger = Logger.getLogger(AESUtil.class);
    /**
     * initSecretKey:(初始化密钥). <br/>
     */
    public static byte[] initSecretKey(String password) {
        //返回生成指定算法的秘密密钥的 KeyGenerator 对象
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        }
        /*初始化此密钥生成器，使其具有确定的密钥大小
         *AES 要求密钥长度为 128
         *1.设了密钥
         *2.使用默认密钥
         *3.没有设密钥
         */
        if (StringUtils.isNotBlank(password)) {
        	kg.init(128, new SecureRandom(password.getBytes()));
        } else if (StringUtils.isNotBlank(AES_PRIVATE_KEY)) {
        	kg.init(128, new SecureRandom(AES_PRIVATE_KEY.getBytes()));
        } else {
        	kg.init(128);
        }        
        //生成一个密钥
        SecretKey  secretKey = kg.generateKey();
        return secretKey.getEncoded();
    }
    
    /**
     * toKey:(生成密钥). <br/>
     */
    private static Key toKey(byte[] key){        
        return new SecretKeySpec(key, KEY_ALGORITHM);
    }
  
    
    //========================   encrypt start   =======================//
    public static byte[] encrypt(byte[] data, Key key) throws Exception{
        return encrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
    }      
   
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception{
        return encrypt(data, key, DEFAULT_CIPHER_ALGORITHM);
    }
         
    public static byte[] encrypt(byte[] data,byte[] key,String cipherAlgorithm) throws Exception{
        //还原密钥
        Key k = toKey(key);
        return encrypt(data, k, cipherAlgorithm);
    }      
   
    public static byte[] encrypt(byte[] data,Key key,String cipherAlgorithm) throws Exception{
        //实例化
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        //使用密钥初始化，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, key);
        //执行操作
        return cipher.doFinal(data);
    }
    
    public static String encrypt(String toEncryptData){
    	return encrypt(toEncryptData, "");
    } 
    
    public static String encrypt(String toEncryptData, String password){
    	try {
    		Key key = toKey(initSecretKey(password));
        	byte[] encryptDataByte = encrypt(toEncryptData.getBytes(), key);
        	return parseByte2HexStr(encryptDataByte);
		} catch (Exception e) {
			logger.error("AESUtil.encrypt 加密失败", e);			
		}
    	return null;
    }   
    //========================   encrypt end   =======================//   
   
    
    
    
    //========================   decrypt start   =======================//
    public static byte[] decrypt(byte[] data,byte[] key) throws Exception{
        return decrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
    }       
   
    public static byte[] decrypt(byte[] data,Key key) throws Exception{
        return decrypt(data, key,DEFAULT_CIPHER_ALGORITHM);
    }       
   
    public static byte[] decrypt(byte[] data,byte[] key,String cipherAlgorithm) throws Exception{
        //还原密钥
        Key k = toKey(key);
        return decrypt(data, k, cipherAlgorithm);
    }
   
    public static byte[] decrypt(byte[] data,Key key,String cipherAlgorithm) throws Exception{
        //实例化
        Cipher cipher = Cipher.getInstance(cipherAlgorithm);
        //使用密钥初始化，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, key);
        //执行操作
        return cipher.doFinal(data);
    }
    
    public static String decrypt(String toDecrptData) {
    	return decrypt(toDecrptData, "");
    } 
    
    public static String decrypt(String toDecrptData, String password) {
    	try {
    		Key key = toKey(initSecretKey(password));
        	byte[] decrptDataByte = decrypt(parseHexStr2Byte(toDecrptData), key);
        	return new String(decrptDataByte);
		} catch (Exception e) {
			logger.error("AESUtil.decrypt 解密失败", e);			
		}
    	return null;
    } 
  //========================   decrypt end   =======================//
    
    
    
    /**将二进制转换成16进制 
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
    
    /**将16进制转换为二进制 
     * @param hexStr 
     * @return 
     */  
    public static byte[] parseHexStr2Byte(String hexStr) {  
            if (hexStr.length() < 1)  
                    return null;  
            byte[] result = new byte[hexStr.length()/2];  
            for (int i = 0;i< hexStr.length()/2; i++) {  
                    int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
                    int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
                    result[i] = (byte) (high * 16 + low);  
            }  
            return result;  
    } 
    
    
    @SuppressWarnings("unused")
	private static String  showByteArray(byte[] data){
        if(null == data){
            return null;
        }
        StringBuilder sb = new StringBuilder("{");
        for(byte b:data){
            sb.append(b).append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("}");
        return sb.toString();
    }
       
    public static void main(String[] args) throws Exception {
    	
//        byte[] key = initSecretKey();
//        System.out.println("key："+showByteArray(key));
//           
//        Key k = toKey(key);
//           
//        String data ="AES数据";
//        System.out.println("加密前数据: string:"+data);
//        System.out.println("加密前数据: byte[]:"+showByteArray(data.getBytes()));
//        System.out.println("解密后数据: string:"+new String(Base64.encodeBase64String(data.getBytes())));
//        System.out.println(showByteArray(Base64.decodeBase64("QUVT5pWw5o2u")));
//        System.out.println();
//        byte[] encryptData = encrypt(data.getBytes(), k);
//        System.out.println("加密后数据: byte[]:"+showByteArray(encryptData));
//        System.out.println();
//        byte[] decryptData = decrypt(encryptData, k);
//        System.out.println("解密后数据: byte[]:"+showByteArray(decryptData));
//        System.out.println("解密后数据: string:"+new String(decryptData));
        
        System.out.println(encrypt("cgw58258547"));
        System.out.println(decrypt("3CFA0B8B051782FD782E9728DCCE0681","12345"));
        System.out.println(decrypt(encrypt("cgw58258547")));
        
    }
}


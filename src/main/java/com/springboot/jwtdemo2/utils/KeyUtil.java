package com.springboot.jwtdemo2.utils;

import java.io.*;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 * @Description: 获取公私钥的工具类
 * @Author 傅琦
 * @Date 2019/4/13 10:19
 * @Version V1.0
 */
public class KeyUtil {
    /**
     * 读取私钥文件获取私钥
     * @param keyPath
     * @return PrivateKey
     */
    public static PrivateKey getPrivateKey(String keyPath){
        DataInputStream dis = null;
        try {
            File file = new File(keyPath);
            FileInputStream fis = new FileInputStream(file);
            dis = new DataInputStream(fis);
            byte[] keyBytes = new byte[(int)file.length()];
            dis.readFully(keyBytes);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (dis != null){
                try {
                    dis.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取公钥文件获取公钥
     * @param keyPath
     * @return PublicKey
     */
    public static PublicKey getPublicKey(String keyPath){
        DataInputStream dis = null;
        try {
            File file = new File(keyPath);
            FileInputStream fis = new FileInputStream(file);
            dis = new DataInputStream(fis);
            byte[] keyBytes = new byte[(int)file.length()];
            dis.readFully(keyBytes);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (dis != null){
                try {
                    dis.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

//    public static PrivateKey obtainPriKey(String filePath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
//        File file = new File(filePath);
//        FileInputStream fis = new FileInputStream(file);
//        DataInputStream dis = new DataInputStream(fis);
//        byte[] keyBytes = new byte[(int)file.length()];
//        dis.readFully(keyBytes);
//        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        return keyFactory.generatePrivate(keySpec);
//    }
}

package com.minigptdemo.utils.encryption;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtil {

    /**
     * 私有化实例
     */
    private EncryptUtil(){}


    public static String SHA(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

            // 将字节数组转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 鉴定哈希值是否相同
     * @param inputPassword 输入的东西
     * @param real    正确的东西
     */
    public static boolean verify(String inputPassword, String real) {
        String hashedInput = SHA(inputPassword);
        return real.equals(hashedInput);
    }

}

package com.whoiszxl.core.utils;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * @description: config加密工具类
 * @author: whoiszxl
 * @create: 2020-01-09
 **/
public class EncryptorUtils {


    public static String encode(String key, String encodeStr) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(key);
        return textEncryptor.encrypt(encodeStr);
    }

    public static String decode(String key, String encodeStr) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(key);
        return textEncryptor.decrypt(encodeStr);
    }

    public static void main(String[] args) {
        String encodeUrl = encode("whoiszxlzxl", "admin1");
        String encodeUsername = encode("whoiszxlzxl", "123");
        String encodePassword = encode("whoiszxlzxl", "http://106.13.218.136:19001");

        System.out.println(encodeUrl);
        System.out.println(encodeUsername);
        System.out.println(encodePassword);
    }
}

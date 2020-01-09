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
        String encodeUrl = encode("xx", "jdbc:mysql://your_database_url:3306/database");
        String encodeUsername = encode("xx", "root");
        String encodePassword = encode("xx", "123456");

        System.out.println(encodeUrl);
        System.out.println(encodeUsername);
        System.out.println(encodePassword);
    }
}

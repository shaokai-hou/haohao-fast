package com.haohao.fast.common.util;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * @author haohao
 * @date 2022年05月31日 11:44
 */
public class JasyptUtils {

    public static void main(String[] args) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("123456");

        String userName = textEncryptor.encrypt("root");
        String passWord = textEncryptor.encrypt("root");

        System.out.println("userName:"+userName);
        System.out.println("passWord:"+passWord);

    }
}

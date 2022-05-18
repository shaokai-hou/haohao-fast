package com.haohao.fast.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author haohao
 */
public class PasswordEncoderTest {

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
        System.out.println(new BCryptPasswordEncoder().matches("admin123", "$2a$10$OdaLmSr5exv91f5wDDvw0epq2tyLM05HmtT.JQ9mXzZ9tF5TzCNBW"));
    }
}

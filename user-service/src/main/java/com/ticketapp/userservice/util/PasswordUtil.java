package com.ticketapp.userservice.util;


import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Component
public class PasswordUtil {

    public String hashPassword(String password) {


        byte[] bytesPassword = password.getBytes(StandardCharsets.UTF_8);

        try {
            MessageDigest digestPassword = MessageDigest.getInstance("SHA-512");
            StringBuilder builder = new StringBuilder();
            byte[] hashedPassword = digestPassword.digest(bytesPassword);
            for (byte b : hashedPassword) {
                builder.append(String.format("%02x", b));
            }
            return builder.toString();


        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-512 algorithm not found", e);
        }


    }

}

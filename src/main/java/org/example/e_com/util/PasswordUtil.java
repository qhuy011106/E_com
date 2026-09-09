package org.example.e_com.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtil {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    /**
     ma hoa mat khau
     @param rawPassword mat khau dang plaintext
     @return matkhau da ma hoa
     */
    public static String encode(String rawPassword){
        if(rawPassword == null || rawPassword.trim().isEmpty()) return null;
        return encoder.encode(rawPassword);
    }
    //keim tra mat khau
    public static boolean matches(String rawPassword, String encodePassword){
        if(rawPassword == null || encodePassword == null) return false;
        return encoder.matches(rawPassword, encodePassword);
    }
    //kiem tra mat khau da duoc ma hoa hay chua
    public static boolean isEncoded(String password){
        if(password == null ) return false;
        return password.startsWith("$2a$")||password.startsWith("$2b$")||password.startsWith("$2y$");
    }


}

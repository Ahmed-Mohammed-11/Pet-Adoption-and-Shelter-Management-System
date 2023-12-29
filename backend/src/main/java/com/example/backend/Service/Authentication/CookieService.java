package com.example.backend.Service.Authentication;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

@Service
public class CookieService {


    private int lifeTime;

    public Cookie createCookie(String name, String data) {
        Cookie cookie = new Cookie(name, data);
        cookie.setMaxAge(1256464);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        return cookie;
    }

    public Cookie getCookie(HttpServletRequest request, String name) {
        return request.getCookies() == null ? null :
                Arrays.stream(request.getCookies())
                        .filter(cookie -> cookie.getName().equals(name))
                        .findFirst()
                        .orElse(null);
    }

    public String hashCode(String input) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(input.getBytes());

        return Base64.getEncoder().encodeToString(hashBytes);
    }
}

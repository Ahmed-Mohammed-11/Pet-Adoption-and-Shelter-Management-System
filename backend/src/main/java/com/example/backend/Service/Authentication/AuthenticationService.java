package com.example.backend.Service.Authentication;

import com.example.backend.Util.JWTUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private AuthenticationManager authenticationManager;
    private JDBCUserDetailsService userDetailsService;
    private JWTUtil jwtUtil;
    private CookieService cookieService;

    public void authenticate(HttpServletResponse httpServletResponse,
                             String userName, String password) throws BadCredentialsException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userName, password));
        String token = jwtUtil.generateToken(userDetailsService.loadUserByUsername(userName));

        Cookie cookie = cookieService.createCookie("token", token);
        httpServletResponse.addCookie(cookie);
    }
}
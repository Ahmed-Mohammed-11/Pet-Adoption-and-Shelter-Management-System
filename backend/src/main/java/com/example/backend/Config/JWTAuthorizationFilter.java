package com.example.backend.Config;

import com.example.backend.Service.Authentication.JDBCUserDetailsService;
import com.example.backend.Util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@AllArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private JDBCUserDetailsService userDetailsService;
    private JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        final Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            String jwtToken = null;

            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    jwtToken = cookie.getValue();
                }
            }

            if (jwtToken != null) {
                String userName = jwtUtil.extractUsername(jwtToken);

                if (userName != null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

                    if (jwtUtil.validateToken(jwtToken, userDetails)) {
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails.getUsername(),
                                        userDetails.getPassword(),
                                        userDetails.getAuthorities()
                                );

                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}


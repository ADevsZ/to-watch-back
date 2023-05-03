package com.imf.alumnos.daw.tfg.alexdiaz.towatchback.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
    HttpServletResponse response, 
    FilterChain filterChain) throws ServletException, IOException {
        UsernamePasswordAuthenticationToken usernamePAT = null;
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null) {
            if (bearerToken.startsWith("Bearer ")) {
                String token = bearerToken.replace("Bearer ", "");
                usernamePAT = TokenUtils.getAuthenticationToken(token);
            } else {
                usernamePAT = TokenUtils.getAuthenticationToken(bearerToken);
            }
            SecurityContextHolder.getContext().setAuthentication(usernamePAT);
        }
        
        filterChain.doFilter(request, response);
    }
    
}

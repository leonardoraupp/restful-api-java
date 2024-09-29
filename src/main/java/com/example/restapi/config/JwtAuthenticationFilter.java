package com.example.restapi.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain //
    ) throws ServletException, IOException {
        //        implements jwt checker
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        final JwtService jwtService = new JwtService();

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response); // call the filter chain and return immediately.
            return;
        }
        jwt = authHeader.substring(7); // return the jwt.

        // Extract the username(useremail)
        userEmail = jwtService.getUsername(jwt);
    }
}
// We extends the class OncePerRequestFilter because  this class need to be fired always a request happens in the system.
// This will be the first layer when the client or user hit a http request.
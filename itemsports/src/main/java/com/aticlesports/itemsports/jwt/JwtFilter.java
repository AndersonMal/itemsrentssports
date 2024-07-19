package com.aticlesports.itemsports.jwt;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomerDetailsServices customerDetailsService;

    Claims claims = null;

    private String username = null;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        String token = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            String email = jwtUtil.extractUsername(token);

            if (jwtUtil.isTokenForStore(token)) {
                // Verifica el token para tienda
                if (jwtUtil.validateStoreToken(token)) {
                    List<GrantedAuthority> authorities = jwtUtil.getAuthoritiesFromToken(token);
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(email, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } else if (jwtUtil.isTokenForUser(token)) {
                // Verifica el token para usuario
                if (jwtUtil.validateUserToken(token)) {
                    List<GrantedAuthority> authorities = jwtUtil.getAuthoritiesFromToken(token);
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(email, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
    public Boolean isAdmin(){
        return  "ADMIN".equalsIgnoreCase((String) claims.get("role"));
    }

    public Boolean isUser(){
        return  "USER".equalsIgnoreCase((String) claims.get("role"));
    }

    public String getCurrentUser(){
        return username;
    }
    private void setAuthentication(Claims claims) {
        if (claims.getSubject() != null) {
            Object authoritiesObj = claims.get("authorities");
            if (authoritiesObj instanceof List<?> list) {
                List<SimpleGrantedAuthority> authorities = list.stream()
                        .filter(obj -> obj instanceof String)
                        .map(obj -> new SimpleGrantedAuthority((String) obj))
                        .toList();

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
    }

}

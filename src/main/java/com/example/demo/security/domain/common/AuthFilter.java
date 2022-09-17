package com.example.demo.security.domain.common;

import com.example.demo.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter extends OncePerRequestFilter {
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private UserService userService;

    @Value("${auth.header}")
    private String TOKEN_HEADER;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String header = request.getHeader(TOKEN_HEADER);
        final SecurityContext securityContext = SecurityContextHolder.getContext();

        if (header != null && securityContext.getAuthentication() == null) {
            String token = header.substring("Bearer".length());
            String username = tokenUtil.getUserNameFromToken(token);
            if (username != null) {
                UserDetails userDetails = userService.loadUserByUsername(username);


                if (tokenUtil.isTokenValid(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }


        } else {
            filterChain.doFilter(request, response);
        }
    }
}

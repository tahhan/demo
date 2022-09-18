package com.example.demo.security.controller;

import com.example.demo.security.domain.common.JWTResponse;
import com.example.demo.security.domain.request.LoginRequest;
import com.example.demo.security.domain.common.TokenUtil;
import com.example.demo.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/login")
@RequiredArgsConstructor
public class LoginController {

    private TokenUtil tokenUtil;
    private UserService userService;
    private AuthenticationManager authenticationManager;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, TokenUtil tokenUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenUtil = tokenUtil;
        this.userService = userService;
    }

    @PostMapping(path = "/")
    public JWTResponse login(@RequestBody LoginRequest loginRequest) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUserName());
        String token = tokenUtil.generateToken(userDetails);
        JWTResponse response = new JWTResponse();
        response.setToken(token);
        return response;
    }
}

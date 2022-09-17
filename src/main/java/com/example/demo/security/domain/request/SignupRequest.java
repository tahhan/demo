package com.example.demo.security.domain.request;

import lombok.Data;

@Data
public class SignupRequest {
    private String firstName;
    private String lastName;
    private String password;
    private String email;

}

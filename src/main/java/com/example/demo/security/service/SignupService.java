package com.example.demo.security.service;

import com.example.demo.security.domain.model.AppUser;
import com.example.demo.security.domain.common.JWTResponse;
import com.example.demo.security.domain.request.SignupRequest;
import com.example.demo.security.reposirory.AppRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignupService {

    @Autowired
    private AppRepository appRepository;

    public JWTResponse addNewUser(SignupRequest signupRequest) {
        AppUser appUser = new AppUser();
        appUser.setFirstName(signupRequest.getFirstName());
        appUser.setLastName(signupRequest.getLastName());
        appUser.setEmail(signupRequest.getEmail());
        appUser.setPassword(signupRequest.getPassword());
        appRepository.save(appUser);
        JWTResponse response = new JWTResponse();
        return response;



    }

    public String sayhi() {
        return "hi"
;    }
}

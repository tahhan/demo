package com.example.demo.security.controller;

import com.example.demo.security.domain.request.SignupRequest;
import com.example.demo.security.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/signup")

public class SignupController {

    @Autowired
    private SignupService signupService;


    @PostMapping("/")
    public String postUser(@RequestBody SignupRequest employeeReq ){
        signupService.addNewUser(employeeReq);
        return "hi";


    }
    @GetMapping("/hi")
    public String hi(){
        return signupService.sayhi();
    }
}

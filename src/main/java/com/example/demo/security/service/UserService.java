package com.example.demo.security.service;

import com.example.demo.security.domain.model.AppUser;
import com.example.demo.security.reposirory.AppRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final AppRepository appRepository;

    public UserService(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    @Bean
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
//    UserDetails
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return new User("ammar",passwordEncoder().encode("password"), AuthorityUtils.NO_AUTHORITIES);
        AppUser user = appRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");

        } else return user;
    }

    public void save(AppUser user) {
        this.appRepository.save(user);
    }

    public String sayHello() {
        return "hi";
    }
}

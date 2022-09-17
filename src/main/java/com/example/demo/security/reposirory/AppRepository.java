package com.example.demo.security.reposirory;

import com.example.demo.security.domain.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepository extends JpaRepository<AppUser,Long> {
    AppUser findByEmail(String username);
}

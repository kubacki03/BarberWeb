package com.example.testsecurity;

import com.example.testsecurity.Models.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserApp, String> {
  Optional<UserApp> findByEmail(String email);


}


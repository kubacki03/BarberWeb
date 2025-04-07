package com.example.testsecurity.Service;

import com.example.testsecurity.Models.UserApp;
import com.example.testsecurity.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAppService {

    private final UserRepository userAppRepository;
    private final PasswordEncoder passwordEncoder;

    public UserAppService(UserRepository userAppRepository, PasswordEncoder passwordEncoder) {
        this.userAppRepository = userAppRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public UserApp findByEmail(String email) {
        return userAppRepository.findByEmail(email).get();
    }



    public void saveUser(UserApp userApp) {
        userAppRepository.save(userApp);
    }


}

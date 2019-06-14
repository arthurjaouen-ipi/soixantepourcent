package com.mesi.histoireHeros.service;

import com.mesi.histoireHeros.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Boolean isValidUser(String login, String password) {
        try {
            if (userRepository.findByLogin(login).getPassword().equals(password)) {
                return true;
            }
            return false;
        }
        catch(Exception e) {
            return false;
        }
    }
}

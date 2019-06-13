package com.mesi.histoireHeros.controller;

import com.mesi.histoireHeros.model.User;
import com.mesi.histoireHeros.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/user")
public class UserController {
    private UserRepository userRepository;

    @RequestMapping(method= RequestMethod.GET,
            produces = "application/json",
            value="/isLogged")
    public User isLogged(@RequestBody User user) {
        try {
            if (userRepository.findByLogin(user.getLogin()).getPassword().equals(user.getPassword())) {
                return userRepository.findByLogin(user.getLogin());
            }
            return null;
        }
        catch(Exception e) {
            return null;
        }
    }

    @RequestMapping(method= RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json",
            value="/createUser")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}

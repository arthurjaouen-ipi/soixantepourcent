package com.mesi.histoireHeros.controller;

import com.mesi.histoireHeros.model.User;
import com.mesi.histoireHeros.repository.UserRepository;
import com.mesi.histoireHeros.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @RequestMapping(method= RequestMethod.GET,
            produces = "application/json",
            value="/isLogged")
    public User isLogged(@RequestHeader("login") String login, @RequestHeader("password") String password) throws Exception {
        if (userService.isValidUser(login, password)) {
            return userRepository.findByLogin(login);
        }
        throw new Exception("Le login/password ne matche pas");
    }

    @RequestMapping(method= RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json",
            value="/createUser")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}

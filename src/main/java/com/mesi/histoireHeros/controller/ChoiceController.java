package com.mesi.histoireHeros.controller;

import com.mesi.histoireHeros.repository.ChoiceRepository;
import com.mesi.histoireHeros.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/choice")
public class ChoiceController {
    @Autowired
    private ChoiceRepository choiceRepository;
    @Autowired
    private UserService userService;


}

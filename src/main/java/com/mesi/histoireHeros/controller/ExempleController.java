package com.mesi.histoireHeros.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExempleController {

    @RequestMapping(path = "/hello")
    public String hello(){
        return "hello";
    }
}

package com.mesi.histoireHeros.controller;

import com.mesi.histoireHeros.model.Choice;
import com.mesi.histoireHeros.model.Scene;
import com.mesi.histoireHeros.repository.ChoiceRepository;
import com.mesi.histoireHeros.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/choice")
public class ChoiceController {
    @Autowired
    private ChoiceRepository choiceRepository;
    @Autowired
    private UserService userService;

    @RequestMapping(method= RequestMethod.GET,
            value="/get/{id}")
    public Choice getChoice(@PathVariable("id") int id,
                            @RequestHeader("login") String login, @RequestHeader("password") String password) throws Exception {
        if (login.equals("") && password.equals("") && !choiceRepository.findOne((long) id).getOriginalScene().getStory().getPublic()) {
            throw new Exception("Ce choix est privée");
        }
        else if (!userService.isValidUser(login, password)) {
            throw new Exception("Le login/password ne matche pas");
        }
        else if (!choiceRepository.findOne((long) id).getOriginalScene().getStory().getPublic() &&
                !choiceRepository.findOne((long) id).getOriginalScene().getStory().getLoginAuthor().equals(login)) {
            throw new Exception("Vous n'etes pas autorisé à visualiser cette scene");
        }
        return choiceRepository.findOne((long) id);
    }

    @RequestMapping(method= RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json",
            value="/create")
    public Choice createChoice(@RequestBody Choice choice,
                              @RequestHeader("login") String login, @RequestHeader("password") String password) throws Exception {
        if (!userService.isValidUser(login, password)) {
            throw new Exception("Le login/password ne matche pas");
        }
        if (!choice.getOriginalScene().getStory().getLoginAuthor().equals(login)) {
            throw new Exception("Vous n'etes pas autorisé à modifier cette histoire");
        }
        if (choice.getTargetedScene().getStory().getId() != choice.getOriginalScene().getStory().getId()) {
            throw new Exception("Vous n'etes pas autorisé à lier des histoires differentes");
        }
        return choiceRepository.save(choice);
    }

    @RequestMapping(method= RequestMethod.GET,
            value="/delete/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteChoice(@PathVariable("id") int id,
                            @RequestHeader("login") String login, @RequestHeader("password") String password) throws Exception {
        if (!userService.isValidUser(login, password)) {
            throw new Exception("Le login/password ne matche pas");
        }
        if (!choiceRepository.findOne((long) id).getOriginalScene().getStory().getLoginAuthor().equals(login)) {
            throw new Exception("Vous n'etes pas autorisé à supprimer cette scene");
        }
        choiceRepository.delete(choiceRepository.getOne((long) id));
    }
}

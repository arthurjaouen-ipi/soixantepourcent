package com.mesi.histoireHeros.controller;

import com.mesi.histoireHeros.model.Scene;
import com.mesi.histoireHeros.model.Story;
import com.mesi.histoireHeros.repository.SceneRepository;
import com.mesi.histoireHeros.repository.StoryRepository;
import com.mesi.histoireHeros.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/scene")
public class SceneController {
    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private SceneRepository sceneRepository;
    @Autowired
    private UserService userService;

    @RequestMapping(method= RequestMethod.GET,
            value="/get/{id}")
    public Scene getScene(@PathVariable("id") int id,
                          @RequestHeader("login") String login, @RequestHeader("password") String password) throws Exception {
        if (login.equals("") && password.equals("") && !sceneRepository.findOne((long) id).getStory().getPublic()) {
            throw new Exception("Cette scene est privée");
        }
        else if (!userService.isValidUser(login, password)) {
            throw new Exception("Le login/password ne matche pas");
        }
        else if (!sceneRepository.findOne((long) id).getStory().getPublic() &&
                !sceneRepository.findOne((long) id).getStory().getLoginAuthor().equals(login)) {
            throw new Exception("Vous n'etes pas autorisé à visualiser cette histoire");
        }
        return sceneRepository.findOne((long) id);
    }
}

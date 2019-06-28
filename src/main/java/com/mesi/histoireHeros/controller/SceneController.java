package com.mesi.histoireHeros.controller;

import antlr.ASTNULLType;
import com.mesi.histoireHeros.model.Choice;
import com.mesi.histoireHeros.model.Scene;
import com.mesi.histoireHeros.model.Story;
import com.mesi.histoireHeros.repository.ChoiceRepository;
import com.mesi.histoireHeros.repository.SceneRepository;
import com.mesi.histoireHeros.repository.StoryRepository;
import com.mesi.histoireHeros.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/api/scene")
public class SceneController {
    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private SceneRepository sceneRepository;
    @Autowired
    private ChoiceRepository choiceRepository;
    @Autowired
    private UserService userService;

    @RequestMapping(method= RequestMethod.GET,
            value="/get/{id}")
    public Scene getScene(@PathVariable("id") int id,
                          @RequestHeader("login") String login, @RequestHeader("password") String password) throws Exception {
        if (login.equals("") && password.equals("") && !sceneRepository.findOne((long) id).getStory().getPublic()) {
            throw new Exception("Cette scene est privée");
        }
        else if (!userService.isValidUser(login, password) && !(login.equals("") && password.equals(""))) {
            throw new Exception("Le login/password ne matche pas");
        }
        else if (!sceneRepository.findOne((long) id).getStory().getPublic() &&
                !sceneRepository.findOne((long) id).getStory().getLoginAuthor().equals(login)) {
            throw new Exception("Vous n'etes pas autorisé à visualiser cette scene");
        }
        return sceneRepository.findOne((long) id);
    }

    @RequestMapping(method= RequestMethod.GET,
            value="/get/{id}/choices")
    public List<Choice> getSceneChoices(@PathVariable("id") int id,
                                       @RequestHeader("login") String login, @RequestHeader("password") String password) throws Exception {
        if (login.equals("") && password.equals("") && !sceneRepository.findOne((long) id).getStory().getPublic()) {
            throw new Exception("Cette scene est privée");
        }
        else if (!userService.isValidUser(login, password) && !(login.equals("") && password.equals(""))) {
            throw new Exception("Le login/password ne matche pas");
        }
        if (!sceneRepository.findOne((long) id).getStory().getPublic() &&
                !sceneRepository.findOne((long) id).getStory().getLoginAuthor().equals(login)) {
            throw new Exception("Vous n'etes pas autorisé à visualiser cette scene");
        }
        ArrayList<Choice> l = new ArrayList<>();
        for(Choice i : choiceRepository.findAll()) {
            if (i.getOriginalScene().getId() == id) {
                l.add(i);
            }
        }
        return l;
    }

    @RequestMapping(method= RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json",
            value="/create")
    public Scene createScene(@RequestBody Scene scene,
                             @RequestHeader("login") String login, @RequestHeader("password") String password) throws Exception {
        if (!userService.isValidUser(login, password)) {
            throw new Exception("Le login/password ne matche pas");
        }
        if (!scene.getStory().getLoginAuthor().equals(login)) {
            throw new Exception("Vous n'etes pas autorisé à modifier cette histoire");
        }
        return sceneRepository.save(scene);
    }

    @RequestMapping(method= RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json",
            value="/edit/{id}")
    public Scene editScene(@RequestBody Scene scene, @PathVariable("id") int id,
                           @RequestHeader("login") String login, @RequestHeader("password") String password) throws Exception {
        if (!userService.isValidUser(login, password)) {
            throw new Exception("Le login/password ne matche pas");
        }
        if (!sceneRepository.findOne((long) id).getStory().getLoginAuthor().equals(login)) {
            throw new Exception("Vous n'etes pas autorisé à modifier cette scene");
        }
        Scene editedStory = sceneRepository.getOne((long) id);
        editedStory.setDescription(scene.getDescription());
        editedStory.setImageURL(scene.getImageURL());
        editedStory.setStory(scene.getStory());
        editedStory.setTitle(scene.getTitle());
        return sceneRepository.save(editedStory);
    }

    @RequestMapping(method= RequestMethod.GET,
            value="/delete/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteScene(@PathVariable("id") int id,
                            @RequestHeader("login") String login, @RequestHeader("password") String password) throws Exception {
        if (!userService.isValidUser(login, password)) {
            throw new Exception("Le login/password ne matche pas");
        }
        if (!sceneRepository.findOne((long) id).getStory().getLoginAuthor().equals(login)) {
            throw new Exception("Vous n'etes pas autorisé à supprimer cette scene");
        }
        storyRepository.delete(storyRepository.getOne((long) id));
    }
}

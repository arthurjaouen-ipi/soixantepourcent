package com.mesi.histoireHeros.controller;

import com.mesi.histoireHeros.model.Scene;
import com.mesi.histoireHeros.model.Story;
import com.mesi.histoireHeros.repository.SceneRepository;
import com.mesi.histoireHeros.repository.StoryRepository;
import com.mesi.histoireHeros.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/api/story")
public class StoryController {
    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private SceneRepository sceneRepository;
    @Autowired
    private UserService userService;

    @RequestMapping(method= RequestMethod.GET,
            value="/get")
    public List<Story> getStories(@RequestHeader("login") String login, @RequestHeader("password") String password) throws Exception {
        if (!userService.isValidUser(login, password) && !(login.equals("") && password.equals(""))) {
            throw new Exception("Le login/password ne matche pas");
        }
        ArrayList<Story> l = new ArrayList<>();
        for(Story i : storyRepository.findAll()) {
            if (i.getPublic()) {
                l.add(i);
            }
            else if (i.getLoginAuthor().equals(login)) {
                l.add(i);
            }
        }
        return l;
    }

    @RequestMapping(method= RequestMethod.GET,
            value="/get/{id}")
    public Story getStory(@PathVariable("id") int id,
                          @RequestHeader("login") String login, @RequestHeader("password") String password) throws Exception {
        if (login.equals("") && password.equals("") && !sceneRepository.findOne((long) id).getStory().getPublic()) {
            throw new Exception("Cette scene est privée");
        }
        else if (!userService.isValidUser(login, password)) {
            throw new Exception("Le login/password ne matche pas");
        }
        else if (!storyRepository.findOne((long) id).getPublic() &&
                !storyRepository.findOne((long) id).getLoginAuthor().equals(login)) {
            throw new Exception("Vous n'etes pas autorisé à visualiser cette histoire");
        }
        return storyRepository.findOne((long) id);
    }

    @RequestMapping(method= RequestMethod.GET,
            value="/get/{id}/scenes")
    public List<Scene> getStoryScenes(@PathVariable("id") int id,
                                      @RequestHeader("login") String login, @RequestHeader("password") String password) throws Exception {
        if (login.equals("") && password.equals("") && !storyRepository.findOne((long) id).getPublic()) {
            throw new Exception("Cette histoire est privée");
        }
        else if (!userService.isValidUser(login, password)) {
            throw new Exception("Le login/password ne matche pas");
        }
        if (!storyRepository.findOne((long) id).getPublic() &&
                !storyRepository.findOne((long) id).getLoginAuthor().equals(login)) {
            throw new Exception("Vous n'etes pas autorisé à visualiser cette histoire");
        }
        ArrayList<Scene> l = new ArrayList<>();
        for(Scene i : sceneRepository.findAll()) {
            if (i.getStory().getId() == id) {
                l.add(i);
            }
        }
        return l;
    }

    @RequestMapping(method= RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json",
            value="/create")
    public Story createStory(@RequestBody Story story,
                             @RequestHeader("login") String login, @RequestHeader("password") String password) throws Exception {
        if (!userService.isValidUser(login, password)) {
            throw new Exception("Le login/password ne matche pas");
        }
        return storyRepository.save(story);
    }

    @RequestMapping(method= RequestMethod.POST,
            produces = "application/json",
            consumes = "application/json",
            value="/edit/{id}")
    public Story editStory(@RequestBody Story story, @PathVariable("id") int id,
                           @RequestHeader("login") String login, @RequestHeader("password") String password) throws Exception {
        if (!userService.isValidUser(login, password)) {
            throw new Exception("Le login/password ne matche pas");
        }
        if (!storyRepository.findOne((long) id).getLoginAuthor().equals(login)) {
            throw new Exception("Vous n'etes pas autorisé à modifier cette histoire");
        }
        Story editedStory = storyRepository.getOne((long) id);
        editedStory.setDescription(story.getDescription());
        editedStory.setFirstScene(story.getFirstScene());
        editedStory.setTitle(story.getTitle());
        editedStory.setLoginAuthor(story.getLoginAuthor());
        return storyRepository.save(editedStory);
    }

    @RequestMapping(method= RequestMethod.GET,
            value="/delete/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteStory(@PathVariable("id") int id,
                           @RequestHeader("login") String login, @RequestHeader("password") String password) throws Exception {
        if (!userService.isValidUser(login, password)) {
            throw new Exception("Le login/password ne matche pas");
        }
        if (!storyRepository.findOne((long) id).getLoginAuthor().equals(login)) {
            throw new Exception("Vous n'etes pas autorisé à supprimer cette histoire");
        }
        storyRepository.delete(storyRepository.getOne((long) id));
    }
}

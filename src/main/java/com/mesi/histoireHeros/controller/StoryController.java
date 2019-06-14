package com.mesi.histoireHeros.controller;

import com.mesi.histoireHeros.model.Story;
import com.mesi.histoireHeros.repository.StoryRepository;
import com.mesi.histoireHeros.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/story")
public class StoryController {
    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private UserService userService;

    @RequestMapping(method= RequestMethod.GET,
            value="/get")
    public List<Story> getStories() throws Exception {
        return storyRepository.findAll();
    }

    @RequestMapping(method= RequestMethod.GET,
            value="/get/{id}")
    public Story getStory(@PathVariable("id") int id,
            @RequestHeader("login") String login, @RequestHeader("password") String password) throws Exception {
        if (!userService.isValidUser(login, password)) {
            throw new Exception("Le login/password ne matche pas");
        }
        return storyRepository.findOne((long) id);
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
        storyRepository.delete(storyRepository.getOne((long) id));
    }
}

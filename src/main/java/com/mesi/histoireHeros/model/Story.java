package com.mesi.histoireHeros.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Story implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private String loginAuthor;
    private Boolean isPublic;
    @ManyToOne
    @JoinColumn(name = "firstScene")
    private Scene firstScene;

    public Story() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLoginAuthor() {
        return loginAuthor;
    }

    public void setLoginAuthor(String loginAuthor) {
        this.loginAuthor = loginAuthor;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public Scene getFirstScene() {
        return firstScene;
    }

    public void setFirstScene(Scene firstScene) {
        this.firstScene = firstScene;
    }
}

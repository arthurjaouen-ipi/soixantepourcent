package com.mesi.histoireHeros.model;

import javax.persistence.*;

@Entity
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String description;
    private String loginAuthor;
    private Boolean isPublic;
    @ManyToOne
    private Scene firstScene;

    public Story() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

package com.mesi.histoireHeros.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Choice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "idOriginalScene")
    private Scene originalScene;
    @ManyToOne
    @JoinColumn(name = "idTargettedScene")
    private Scene targetedScene;
    private String name;

    public Choice() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Scene getOriginalScene() {
        return originalScene;
    }

    public void setOriginalScene(Scene originalScene) {
        this.originalScene = originalScene;
    }

    public Scene getTargetedScene() {
        return targetedScene;
    }

    public void setTargetedScene(Scene targetedScene) {
        this.targetedScene = targetedScene;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

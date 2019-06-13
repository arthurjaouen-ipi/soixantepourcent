package com.mesi.histoireHeros.model;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class Choice {
    @Id
    @ManyToOne
    private int idOriginalScene;
    @Id
    @ManyToOne
    private int idTargetedScene;
    private String name;

    public Choice() {
    }

    public int getIdOriginalScene() {
        return idOriginalScene;
    }

    public void setIdOriginalScene(int idOriginalScene) {
        this.idOriginalScene = idOriginalScene;
    }

    public int getIdTargetedScene() {
        return idTargetedScene;
    }

    public void setIdTargetedScene(int idTargetedScene) {
        this.idTargetedScene = idTargetedScene;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.mesi.histoireHeros.model;

public class Choice {
    private int idOriginalScene;
    private int idTargetedScene;
    private String name;

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

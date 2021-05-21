package com.example.drttreasurefinder;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String name;
    private ArrayList<Trial> trials;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Trial> getTrials() {
        return trials;
    }

    public User() {}

    public User(String name) {
        trials = new ArrayList<>();
        setName(name);
    }
}

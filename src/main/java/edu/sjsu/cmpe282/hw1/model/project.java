package edu.sjsu.cmpe282.hw1.model;

/**
 * Created by dexter on 2/29/16.
 */
public class project {
    private int id;
    private String name;
    private float budget;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public project() {
    }

    public project(int id, String name, float budget) {
        this.id = id;
        this.name = name;
        this.budget = budget;
    }
}

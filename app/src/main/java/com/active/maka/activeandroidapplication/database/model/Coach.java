package com.active.maka.activeandroidapplication.database.model;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;


@Table(name = "Coach")
public class Coach extends Model {

    @Column(name = "name")
    private String name;

    public Coach() {

    }

    public Coach(String coachName) {
        name = coachName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

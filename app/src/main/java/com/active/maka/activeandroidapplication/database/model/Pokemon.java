package com.active.maka.activeandroidapplication.database.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Pokemon")
public class Pokemon extends Model {

    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private Type type;

    public Pokemon() {
    }

    public Pokemon(String pokemonName, Type pokemonType) {
        name = pokemonName;
        type = pokemonType;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public enum Type {
        GRASS, WATER, ELECTRIC, FIRE
    }
}

package com.active.maka.activeandroidapplication.database.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "CoachPokemonRelation")
public class CoachPokemonRelation extends Model {

    @Column(name = "Coach")
    private Coach mCoach;
    @Column(name = "Pokemon")
    private Pokemon mPokemon;

    public CoachPokemonRelation() {
    }

    public CoachPokemonRelation(Coach coach, Pokemon pokemon) {
        mCoach = coach;
        mPokemon = pokemon;
    }

    public Coach getCoach() {
        return mCoach;
    }

    public void setCoach(Coach coach) {
        mCoach = coach;
    }

    public Pokemon getPokemon() {
        return mPokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        mPokemon = pokemon;
    }
}

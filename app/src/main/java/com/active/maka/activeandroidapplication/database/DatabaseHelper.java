package com.active.maka.activeandroidapplication.database;

import android.support.annotation.Nullable;

import com.active.maka.activeandroidapplication.database.model.Coach;
import com.active.maka.activeandroidapplication.database.model.CoachPokemonRelation;
import com.active.maka.activeandroidapplication.database.model.Pokemon;
import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

import java.util.List;

public class DatabaseHelper {

    private static DatabaseHelper databaseHelper;

    private DatabaseHelper() {
    }

    public static DatabaseHelper getInstance() {
        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper();
        }

        return databaseHelper;
    }

    public boolean savePokemon(Pokemon pokemon) {
        return pokemon.save() > -1;
    }

    public boolean saveCoach(Coach coach) {
        return coach.save() > -1;
    }

    public boolean saveCoachPokemonRelation(CoachPokemonRelation coachPokemonRelation) {
        return coachPokemonRelation.save() > -1;
    }

    public boolean savePokemonList(List<Pokemon> pokemonList) {
        try {
            ActiveAndroid.beginTransaction();
            for (Pokemon pokemon : pokemonList) {
                if (!savePokemon(pokemon)) {
                    return false;
                }
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
        return true;
    }

    public boolean saveCoachList(List<Coach> coachList) {
        try {
            ActiveAndroid.beginTransaction();
            for (Coach coach : coachList) {
                if (!saveCoach(coach)) {
                    return false;
                }
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
        return true;
    }

    public boolean saveCoachPokemonRelationList(List<CoachPokemonRelation> coachPokemonRelationList) {
        try {
            ActiveAndroid.beginTransaction();
            for (CoachPokemonRelation coachPokemonRelation : coachPokemonRelationList) {
                if (!saveCoachPokemonRelation(coachPokemonRelation)) {
                    return false;
                }
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
        return true;
    }

    public static List<Pokemon> getAllPokemons() {
        return new Select()
                .from(Pokemon.class)
                .execute();
    }

    public static List<Coach> getAllCoaches() {
        return new Select()
                .from(Coach.class)
                .execute();
    }

}

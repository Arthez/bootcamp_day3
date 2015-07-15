package com.active.maka.activeandroidapplication.ui;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.active.maka.activeandroidapplication.R;
import com.active.maka.activeandroidapplication.database.DatabaseHelper;
import com.active.maka.activeandroidapplication.database.model.Coach;
import com.active.maka.activeandroidapplication.database.model.CoachPokemonRelation;
import com.active.maka.activeandroidapplication.database.model.Pokemon;
import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends ActionBarActivity {

    @Bind(R.id.text_pokemon)
    TextView mPokemonTextView;

    @Bind(R.id.text_owners_pokemons)
    TextView mOwnersPokemonTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        savePokemonList();
    }

    private void savePokemonList() {
        new SavePokemonTask().execute();
    }

    private void saveCoachList() {
        new SaveCoachTask().execute();
    }

    private void saveCoachPokemonRelation() {
        new SaveCoachPokemonRelationTask().execute();
    }

    @OnClick(R.id.button_coach1)
    public void onClickCoach1() {
        mOwnersPokemonTextView.setText(getPokemonListString(1));
    }

    @OnClick(R.id.button_coach2)
    public void onClickCoach2() {
        mOwnersPokemonTextView.setText(getPokemonListString(2));
    }

    private String getPokemonListString(int coachId) {
        List<CoachPokemonRelation> coachPokemonRelationList = new Select()
                .from(CoachPokemonRelation.class)
                .where("coach = ?", coachId)
                .execute();

        List<Pokemon> pokemonList = new ArrayList<>();
        for (CoachPokemonRelation couchPokemonRelationItem : coachPokemonRelationList) {
            pokemonList.add(couchPokemonRelationItem.getPokemon());
        }

        String pokemonOwnerListString = getString(R.string.owner_header);
        for (int pokemonIndex = 0; pokemonIndex < pokemonList.size(); pokemonIndex++) {
            pokemonOwnerListString += pokemonList.get(pokemonIndex).getName() + " |";
        }

        return pokemonOwnerListString;
    }

    private class SaveCoachTask extends AsyncTask<Void, Void, List<Coach>> {

        @Override
        protected List<Coach> doInBackground(Void... params) {
            List<Coach> coachList = createCoaches();
            if (DatabaseHelper.getInstance().saveCoachList(coachList)) {
                return coachList;
            }

            return null;
        }

        private List<Coach> createCoaches() {
            List<Coach> coachList = new ArrayList<>();
            coachList.add(new Coach("Oak"));
            coachList.add(new Coach("Ash"));

            return coachList;
        }

        @Override
        protected void onPostExecute(@Nullable List<Coach> coachList) {
            super.onPostExecute(coachList);
            saveCoachPokemonRelation();
        }
    }

    private class SavePokemonTask extends AsyncTask<Void, Void, List<Pokemon>> {

        @Override
        protected List<Pokemon> doInBackground(Void... params) {
            List<Pokemon> pokemonList = createPokemonList();

            if (DatabaseHelper.getInstance().savePokemonList(pokemonList)) {
                return pokemonList;
            }

            return null;
        }

        private List<Pokemon> createPokemonList() {
            List<Pokemon> pokemonList = new ArrayList<>();

            pokemonList.add(new Pokemon("Pikachu", Pokemon.Type.ELECTRIC));
            pokemonList.add(new Pokemon("Raichu", Pokemon.Type.ELECTRIC));
            pokemonList.add(new Pokemon("Bulbasaur", Pokemon.Type.GRASS));
            pokemonList.add(new Pokemon("Charmander", Pokemon.Type.FIRE));
            pokemonList.add(new Pokemon("Squirtle", Pokemon.Type.WATER));
            pokemonList.add(new Pokemon("Venosaur", Pokemon.Type.GRASS));
            pokemonList.add(new Pokemon("Charizard", Pokemon.Type.FIRE));
            pokemonList.add(new Pokemon("Blastoise", Pokemon.Type.WATER));
            pokemonList.add(new Pokemon("Charmeleon", Pokemon.Type.FIRE));
            pokemonList.add(new Pokemon("Wartortle", Pokemon.Type.WATER));

            return pokemonList;
        }

        @Override
        protected void onPostExecute(@Nullable List<Pokemon> pokemons) {
            super.onPostExecute(pokemons);
            if (pokemons != null) {
                mPokemonTextView.setText(getString(R.string.all_pokemon_header));
                for (Pokemon pokemon : pokemons) {
                    mPokemonTextView.append(pokemon.getName() + "|");
                }
            } else {
                mPokemonTextView.setText("An error occurred...");
            }

            saveCoachList();
        }
    }


    private class SaveCoachPokemonRelationTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            List<CoachPokemonRelation> coachPokemonRelationList = createCoachPokemonRelation();
            DatabaseHelper.getInstance().saveCoachPokemonRelationList(coachPokemonRelationList);
            return null;
        }

        private List<CoachPokemonRelation> createCoachPokemonRelation() {
            List<Coach> coachList = DatabaseHelper.getAllCoaches();
            List<Pokemon> pokemonList = DatabaseHelper.getAllPokemons();

            List<CoachPokemonRelation> coachPokemonRelationList = new ArrayList<>();

            for (int coachIndex = 0; coachIndex < coachList.size(); coachIndex++) {

                for (int pokemonIndex = 0; pokemonIndex < pokemonList.size() - 1; pokemonIndex++) {
                    coachPokemonRelationList.add(new CoachPokemonRelation(coachList.get(coachIndex), pokemonList.get(pokemonIndex)));
                }
            }

            return coachPokemonRelationList;
        }

    }


}

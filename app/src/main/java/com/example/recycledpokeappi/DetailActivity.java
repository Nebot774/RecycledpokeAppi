package com.example.recycledpokeappi;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Encuentra el TextView en el layout
        TextView textViewPokemonName = findViewById(R.id.textViewPokemonName);

        // Recibe el nombre del Pokémon de la actividad anterior
        String pokemonName = getIntent().getStringExtra("pokemon_name");

        // Establece el nombre del Pokémon en el TextView
        textViewPokemonName.setText(pokemonName);
    }
}


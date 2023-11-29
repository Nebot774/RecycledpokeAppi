package com.example.recycledpokeappi;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PokemonAdapter.ItemClickListener {

    PokemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Datos para el RecyclerView
        List<Pokemon> pokemonList = new ArrayList<>();

        // Configura el RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PokemonAdapter(this, pokemonList);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        // Cargar los datos
        cargarDatos();
    }

    @Override
    public void onItemClick(View view, int position) {
        // Maneja el clic en un item
        // Puedes iniciar una nueva actividad pasando detalles del Pokémon seleccionado
    }

    private void cargarDatos() {
        String url = "https://pokeapi.co/api/v2/pokemon-species/";

        // Crear una solicitud de tipo String
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Parsear la respuesta usando Gson
                        // Parsear la respuesta usando Gson
                        Gson gson = new Gson();
                        PokemonResponse pokemonResponse = gson.fromJson(response, PokemonResponse.class);

                        // Actualizar el adaptador del RecyclerView
                        adapter.setPokemonList(pokemonResponse.getResults());
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Manejar el error
                error.printStackTrace();
            }
        });

        // Agregar la solicitud a la cola de Volley
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

}


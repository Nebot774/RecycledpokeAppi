package com.example.recycledpokeappi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
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

        // Creación del objeto ItemTouchHelper.SimpleCallback
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                // No es necesario manejar movimientos en este caso
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int swipeDir) {
                // Maneja el evento de arrastre
                int position = viewHolder.getAdapterPosition(); // Obtiene la posición del elemento deslizado
                Pokemon pokemon = adapter.getPokemonAtPosition(position); // Obtiene el Pokémon en esa posición
                mostrarDetalle(pokemon); // Llama al método para mostrar los detalles

                // Importante: Notifica al adaptador que el elemento no se ha eliminado
                adapter.notifyItemChanged(position);
            }
        };

// Adjunta el ItemTouchHelper al RecyclerView
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);




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

    //metodo para manejar la transacion a la actividad de detalles
    private void mostrarDetalle(Pokemon pokemon) {
        // Crea un Intent para iniciar DetailActivity
        Intent intent = new Intent(this, DetailActivity.class);

        // Pasa el nombre del Pokémon a DetailActivity
        intent.putExtra("pokemon_name", pokemon.getName());

        // Inicia la actividad
        startActivity(intent);
    }




}


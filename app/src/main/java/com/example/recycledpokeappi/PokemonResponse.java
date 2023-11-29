package com.example.recycledpokeappi;

import java.util.List;

//clase para manejar la respuesta de la api que incluye una lista de Pokemons que nos devolvera la API
public class PokemonResponse {
    private List<Pokemon> results;

    // Constructor
    public PokemonResponse(List<Pokemon> results) {
        this.results = results;
    }

    // Getter para obtener la lista de Pokemon
    public List<Pokemon> getResults() {
        return results;
    }

    // Setter para establecer la lista de Pokemon
    public void setResults(List<Pokemon> results) {
        this.results = results;
    }
}


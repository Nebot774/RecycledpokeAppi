package com.example.recycledpokeappi;

public class Pokemon {
    private String name;
    private String url;

    // Constructor por defecto
    public Pokemon() {
    }

    // Constructor con parámetros
    public Pokemon(String name, String url) {
        this.name = name;
        this.url = url;
    }

    // Getter para obtener el nombre
    public String getName() {
        return name;
    }

    // Setter para establecer el nombre
    public void setName(String name) {
        this.name = name;
    }

    // Getter para obtener la URL
    public String getUrl() {
        return url;
    }

    // Setter para establecer la URL
    public void setUrl(String url) {
        this.url = url;
    }
}

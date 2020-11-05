package com.webservice.pokedex.entities;

import io.swagger.v3.oas.annotations.media.Schema;

public class Type {

    @Schema(description = "Name of the pokemon type", example = "normal")
    private String name;
    @Schema(description = "Url of the pokemon type", example = "https://pokeapi.co/api/v2/type/1/")
    private String url;

    public Type() {}

    public Type(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

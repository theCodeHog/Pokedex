package com.webservice.pokedex.entities;

import org.springframework.data.annotation.Id;
import java.util.List;

public class Pokemon {

    @Id
    private String id;
    private String name;
    private Object species;
    private int weight;
    private int height;
    private List<Object> abilities;
    private List<PokemonType> pokemonTypes;

    public Pokemon(){}

    public Pokemon(String id, String name, Object species, int weight, int height,
                   List<Object> abilities, List<PokemonType> pokemonTypes) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.weight = weight;
        this.height = height;
        this.abilities = abilities;
        this.pokemonTypes = pokemonTypes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getSpecies() {
        return species;
    }

    public void setSpecies(Object species) {
        this.species = species;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Object> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Object> abilities) {
        this.abilities = abilities;
    }

    public List<PokemonType> getPokemonTypes() {
        return pokemonTypes;
    }

    public void setPokemonTypes(List<PokemonType> pokemonTypes) {
        this.pokemonTypes = pokemonTypes;
    }
}

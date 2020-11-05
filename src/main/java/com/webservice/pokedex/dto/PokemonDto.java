package com.webservice.pokedex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.webservice.pokedex.entities.PokemonType;
import java.util.List;

//DTO = Data Transfer Object
public class PokemonDto {

    private String id;
    private String name;
    private Object species;
    private int weight;
    private int height;
    private List<Object> abilities;
    private List<PokemonType> types;

    public PokemonDto(){}

    public PokemonDto(String name, Object species, int weight, int height,
                   List<Object> abilities, List<PokemonType> types) {
        this.name = name;
        this.species = species;
        this.weight = weight;
        this.height = height;
        this.abilities = abilities;
        this.types = types;
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

    public List<PokemonType> getTypes() {
        return types;
    }

    public void setTypes(List<PokemonType> types) {
        this.types = types;
    }
}

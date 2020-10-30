package com.webservice.pokedex.dto;

import java.util.List;

//DTO = Data Transfer Object
public class PokemonDto {

    private String id;
    private String name;
    private Object species;
    private int weight;
    private int height;
    private List<Object> abilities;

    public PokemonDto(){}

    public PokemonDto(String name, Object species, int weight, int height,
                   List<Object> abilities) {
        this.name = name;
        this.species = species;
        this.weight = weight;
        this.height = height;
        this.abilities = abilities;
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

}

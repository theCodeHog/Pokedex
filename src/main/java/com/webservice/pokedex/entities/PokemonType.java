package com.webservice.pokedex.entities;

import io.swagger.v3.oas.annotations.media.Schema;

public class PokemonType {

    @Schema(description = "Index number", example = "1")
    private int slot;
    @Schema(description = "Type of the pokemon, i.e. dark, normal")
    private Type type;

    public PokemonType(){}

    public PokemonType(int slot, Type type) {
        this.slot = slot;
        this.type = type;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}

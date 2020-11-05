package com.webservice.pokedex.entities;

public class PokemonType {

    private int slot;
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

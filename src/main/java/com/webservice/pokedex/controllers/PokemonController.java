package com.webservice.pokedex.controllers;

import com.webservice.pokedex.entities.Pokemon;
import com.webservice.pokedex.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/rest/v1/pokemon")
public class PokemonController {

    @Autowired
    PokemonService pokemonService;

    @GetMapping("/{id}")
    public Optional<Pokemon> getPokemonById(@PathVariable String id) {
        return pokemonService.findById(id);
    }

}

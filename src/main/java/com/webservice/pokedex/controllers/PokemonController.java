package com.webservice.pokedex.controllers;

import com.webservice.pokedex.entities.Pokemon;
import com.webservice.pokedex.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rest/v1/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping()
    public ResponseEntity<Pokemon> findPokemon(@RequestParam String name){
        var pokemon = pokemonService.search(name);
        return ResponseEntity.ok(pokemon);
    }

    @GetMapping("/{id}")
    public Optional<Pokemon> getPokemonById(@PathVariable String id) {
        return pokemonService.findById(id);
    }

}

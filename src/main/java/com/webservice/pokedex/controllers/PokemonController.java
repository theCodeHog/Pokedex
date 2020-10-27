package com.webservice.pokedex.controllers;

import com.webservice.pokedex.entities.Pokemon;
import com.webservice.pokedex.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/v1/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    //find pokemon by name or id
    @GetMapping("/{name}")
    public ResponseEntity<List<Pokemon>> findPokemon(@PathVariable String name){
        List<Pokemon> pokemons = (List<Pokemon>) pokemonService.search(name);
        return ResponseEntity.ok(pokemons);
    }

    /*@GetMapping("/{id}")
    public Optional<Pokemon> getPokemonById(@PathVariable String id) {
        return pokemonService.findById(id);
    }*/

}

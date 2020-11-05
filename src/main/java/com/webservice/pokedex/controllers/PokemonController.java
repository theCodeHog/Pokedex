package com.webservice.pokedex.controllers;

import com.webservice.pokedex.entities.Pokemon;
import com.webservice.pokedex.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rest/v1/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    //find pokemon by name or other parameters?
    @GetMapping("/{name}")
    public ResponseEntity<List<Pokemon>> findPokemon(@PathVariable String name) throws NoSuchFieldException, IllegalAccessException {
        List<Pokemon> pokemons = pokemonService.search(name);
        return ResponseEntity.ok(pokemons); //Returns statuscode 200
    }

    //search for pokemon with four parameters (ex: name=sand and height>30, weight>30, abilities.name=push)

    @GetMapping("/pokemons") // /pokemons?name=raticate&height=7&weight=185&type=normal
    public ResponseEntity<List<Pokemon>> getPokemonbyNameHeightWeightType(
            @RequestParam String name, //you -have- to search by name - based on how PokeApi works
            @RequestParam(required = false) String height,
            @RequestParam(required = false) String weight,
            @RequestParam(required = false) String type ){
        List<Pokemon> pokemons = pokemonService.searchByMultipleThings(name, height, weight, type);
        return ResponseEntity.ok(pokemons); //Returns statuscode 200
    }

}

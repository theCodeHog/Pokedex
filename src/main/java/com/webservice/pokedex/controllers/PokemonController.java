package com.webservice.pokedex.controllers;

import com.webservice.pokedex.entities.Pokemon;
import com.webservice.pokedex.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
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

    //search with partial string name

    //search for pokemon with four parameters (ex: name=sand and height>30, weight>30, abilities.name=push)

    @GetMapping()
    public ResponseEntity<List<Pokemon>> getPokemonbyNameHeightWeightAbilities(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String height,
            @RequestParam(required = false) String weight,
            @RequestParam(required = false) String abilities ){
        List<Pokemon> pokemons = pokemonService.searchByMultipleThings(name, height, weight, abilities);
        return ResponseEntity.ok(pokemons);
    }

    /*
    @GetMapping("/users") // /rest/users?name=johan
    public List<User> getUserWithName(@RequestParam(required = false) String name){
        return userService.findUsers(name);
    }*/




}

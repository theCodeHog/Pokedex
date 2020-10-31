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

    //find pokemon by name or id
    @GetMapping("/{nameOrId}")
    public ResponseEntity<List<Pokemon>> findPokemon(@PathVariable String nameOrId) throws NoSuchFieldException, IllegalAccessException {
        List<Pokemon> pokemons = (List<Pokemon>) pokemonService.search(nameOrId);
        return ResponseEntity.ok(pokemons);
    }

    /*@GetMapping("/{id}")
    public Optional<Pokemon> getPokemonById(@PathVariable String id) {
        return pokemonService.findById(id);
    }*/

    //search with partial string name

    //search for pokemon with four parameters (ex: name=sand and height>30, weight>30, abilities.name=push)

    @GetMapping("/pokemons") // /pokemons?name=raticate?height=7?weight=185?abilities=run-away
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

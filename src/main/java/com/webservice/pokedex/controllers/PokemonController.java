package com.webservice.pokedex.controllers;

import com.webservice.pokedex.entities.Pokemon;
import com.webservice.pokedex.services.PokemonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rest/v1/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @Operation(summary = "Get all pokemon by name OR height OR weight OR type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found pokemon",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pokemon.class)) }),
            @ApiResponse(responseCode = "404", description = "No pokemon found",
                    content = @Content) })
    @GetMapping("/{name}")
    public ResponseEntity<List<Pokemon>> findPokemon(@Parameter(description = "name of pokemon to be searched")@PathVariable String name) {
        List<Pokemon> pokemons = pokemonService.search(name);
        return ResponseEntity.ok(pokemons);
    }


    @GetMapping("/pokemons") // /pokemons?name=raticate&height=7&weight=185&type=normal
    public ResponseEntity<List<Pokemon>> getPokemonbyNameHeightWeightType(
            @RequestParam String name, //you -have- to search by name - based on how PokeApi works
            @RequestParam(required = false) String height,
            @RequestParam(required = false) String weight,
            @RequestParam(required = false) String type ){
        List<Pokemon> pokemons = pokemonService.searchByMultipleThings(name, height, weight, type);
        return ResponseEntity.ok(pokemons);
    }

}

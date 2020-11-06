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

    @Operation(summary = "Get ONE pokemon by name OR id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found pokemon",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pokemon.class)) }),
            @ApiResponse(responseCode = "404", description = "No pokemon found",
                    content = @Content) })
    @GetMapping("/{nameOrId}")
    public ResponseEntity<Pokemon> findOnePokemonByNameOrId(@Parameter(description = "name or id of pokemon to be searched")@PathVariable String nameOrId) {
        Pokemon pokemon = pokemonService.findByNameOrId(nameOrId);
        return ResponseEntity.ok(pokemon);
    }

    @Operation(summary = "Get ALL pokemon by name AND/OR height AND/OR weight AND/OR type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "pokemon found!",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pokemon.class)) }),
            @ApiResponse(responseCode = "404", description = "No pokemon found",
                    content = @Content) })
    @GetMapping() // /rest/v1/pokemon?name=raticate&weight=185&height=7&type=normal
    public ResponseEntity<List<Pokemon>> getPokemonByNameHeightWeightType(
            @RequestParam String name, //you -have- to search by name - based on how PokeApi works
            @RequestParam(required = false) Integer minWeight,
            @RequestParam(required = false) Integer minHeight,
            @RequestParam(required = false) String type ){
        List<Pokemon> pokemons = pokemonService.searchByNameWeightHeightType(name, minWeight, minHeight, type);
        return ResponseEntity.ok(pokemons);
    }

}

package com.webservice.pokedex.services;

import com.webservice.pokedex.dto.PokemonDto;
import com.webservice.pokedex.entities.Pokemon;
import com.webservice.pokedex.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@ConfigurationProperties(value = "pokemon.url", ignoreUnknownFields = false)
public class PokemonService {
    private final RestTemplate restTemplate;
    private String url = "https://pokeapi.co/api/v2/pokemon/";

    @Autowired
    PokemonRepository pokemonRepository;

    public PokemonService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Pokemon> search(String name){
        var urlWithTitleQuery = url + name;

        //check the database
        var pokemons = pokemonRepository.findAllByName(name);
        //if the pokemon doesn't exist in the db - check PokeApi
        if(pokemons.isEmpty()){
            System.out.println("no pokemon in db");
            var pokemonDto = restTemplate.getForObject(urlWithTitleQuery, PokemonDto.class);
            //if pokemon not found on PokeApi throw exception
            if(pokemonDto == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pokemon found.");
            } //if found create new pokemon and save to db
            else {
                var newPokemon = new Pokemon(
                        pokemonDto.getId(),
                        pokemonDto.getName(),
                        pokemonDto.getSpecies(),
                        pokemonDto.getWeight(),
                        pokemonDto.getHeight(),
                        pokemonDto.getAbilities()
                );
                pokemons.add(this.save(newPokemon));
            }
        }
        return pokemons;
    }

    public List<Pokemon> searchByMultipleThings(String name, String weight, String height, String abilities){
        List<Pokemon> pokemonsByName = pokemonRepository.findAllByName(name);
        List<Pokemon> pokemonsByWeight = pokemonRepository.findAllByWeight(weight);
        List<Pokemon> pokemonsByHeight = pokemonRepository.findAllByHeight(height);
        List<Pokemon> pokemonsByAbilities = pokemonRepository.findAllByAbilities(abilities);

        List<Pokemon> pokemons = Stream.of(pokemonsByName, pokemonsByWeight, pokemonsByHeight, pokemonsByAbilities)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        //remove any duplicates :///

        return pokemons;
    }

    public Pokemon save(Pokemon pokemon){
        return pokemonRepository.save(pokemon);
    }

    public Pokemon findById(String id){
        return pokemonRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "No pokemon found."));
    }

}

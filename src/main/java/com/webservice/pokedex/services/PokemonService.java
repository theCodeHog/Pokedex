package com.webservice.pokedex.services;

import com.webservice.pokedex.dto.PokemonDto;
import com.webservice.pokedex.entities.Pokemon;
import com.webservice.pokedex.entities.PokemonType;
import com.webservice.pokedex.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@Service
@ConfigurationProperties(value = "pokemon.url", ignoreUnknownFields = false) //whats this do???
public class PokemonService {
    private final RestTemplate restTemplate;
    private String url = "https://pokeapi.co/api/v2/pokemon";
    private String urlWithAllPokemons = url + "?limit=2000";

    @Autowired
    private PokemonRepository pokemonRepository;

    //constructor
    public PokemonService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    //setter
    public void setUrl(String url) {
        this.url = url;
    }

    //Search for ONE pokemon
    public Pokemon findByNameOrId(String nameOrId) {
        var urlWithTitleQuery = url + "/" + nameOrId;

        var pokemon = findPokemonInDb(nameOrId);

        if (pokemon.isEmpty()) {
            var pokemonDto = restTemplate.getForObject(urlWithTitleQuery, PokemonDto.class);
            if (pokemonDto == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pokemon found.");
            } else {
                var newPokemon = createNewPokemon(pokemonDto);
                pokemon.add(this.save(newPokemon));
            }
        }
        return pokemon.get(0);
    }

    //Search ALL pokemon
    public List<Pokemon> searchByNameWeightHeightType(String name, Integer minWeight, Integer minHeight, String type) {
        List<Pokemon> pokemonsByName = this.searchForPokemonNamesByPartialString(name);
        List<Pokemon> pokemonsByWeight;
        List<Pokemon> pokemonsByHeight;
        List<Pokemon> pokemonsByType;

        if ((!pokemonsByName.isEmpty()) && (minWeight != null)) {
            pokemonsByWeight = this.searchForPokemonByWeight(pokemonsByName, minWeight);

            if ((!pokemonsByWeight.isEmpty()) && (minHeight != null)) {
                pokemonsByHeight = this.searchForPokemonByHeight(pokemonsByWeight, minHeight);

                if ((!pokemonsByHeight.isEmpty()) && (type != null)) {
                    pokemonsByType = this.searchForPokemonByType(pokemonsByHeight, type);
                    if (!pokemonsByType.isEmpty()){
                        return pokemonsByType;
                    }
                }
                if ((pokemonsByHeight.isEmpty()) && (type != null)) {
                    pokemonsByType = this.searchForPokemonByType(pokemonsByWeight, type);
                    if (!pokemonsByType.isEmpty()){
                        return pokemonsByType;
                    }
                }
                if (!pokemonsByHeight.isEmpty()){
                    return pokemonsByHeight;
                }
            }
            if ((pokemonsByWeight.isEmpty()) && (minHeight != null)) {
                pokemonsByHeight = this.searchForPokemonByHeight(pokemonsByName, minHeight);

                if ((!pokemonsByHeight.isEmpty()) && (type != null)) {
                    pokemonsByType = this.searchForPokemonByType(pokemonsByHeight, type);
                    if (!pokemonsByType.isEmpty()){
                        return pokemonsByType;
                    }
                }
                if ((pokemonsByHeight.isEmpty()) && (type != null)) {
                    pokemonsByType = this.searchForPokemonByType(pokemonsByName, type);
                    if (!pokemonsByType.isEmpty()){
                        return pokemonsByType;
                    }
                }
                if (!pokemonsByHeight.isEmpty()){
                    return pokemonsByHeight;
                }
            }

            if (!pokemonsByWeight.isEmpty() && (type != null)){
                pokemonsByType = this.searchForPokemonByType(pokemonsByWeight, type);
                if (!pokemonsByType.isEmpty()){
                    return pokemonsByType;
                }
            }
            if (!pokemonsByWeight.isEmpty()){
                return pokemonsByWeight;
            }
        }
        if (pokemonsByName.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pokemon found, make a better query!");
        }
        return pokemonsByName;
    }

    private Pokemon save(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    private List<Pokemon> findPokemonInDb(String name) {
        return pokemonRepository.findAllByName(name);
    }

    private List<Pokemon> searchForPokemonNamesByPartialString(String name){
        var pokemons = new ArrayList<Pokemon>();

        //get all pokemon names in a single map
        //this checks everytime if pokeAPI has added a new pokemon to search with
        var allPokemonNames = this.getAllPokemonNamesAndUrls(urlWithAllPokemons);

        //check if allPokemonNames contains data - then get detail info of each pokemon (either from db or pokeApi)
        if (allPokemonNames != null) {
            for (Object x : allPokemonNames) {
                var pokemonMap = (LinkedHashMap) x;
                var pokemonName = pokemonMap.get("name").toString();
                if (pokemonName.contains(name)) {
                    var pokemon = this.findPokemonInDb(pokemonName);
                    //if the pokemon doesn't exist in the db - check PokeApi
                    if (pokemon.isEmpty()) {
                        var pokemonUrl = pokemonMap.get("url").toString();
                        var pokemonDto = restTemplate.getForObject(pokemonUrl, PokemonDto.class);
                        //if pokemon not found on PokeApi throw exception
                        if (pokemonDto == null) {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pokemon found.");
                        } //if found create new pokemon and save to db
                        else {
                            Pokemon newPokemon = createNewPokemon(pokemonDto);
                            pokemons.add(this.save(newPokemon));
                        }
                    } else {
                        pokemons.addAll(pokemon);
                    }
                }
            }
        }
        return pokemons;
    }

    private Pokemon createNewPokemon(PokemonDto pokemonDto){
        return new Pokemon(
                pokemonDto.getId(),
                pokemonDto.getName(),
                pokemonDto.getSpecies(),
                pokemonDto.getWeight(),
                pokemonDto.getHeight(),
                pokemonDto.getAbilities(),
                pokemonDto.getTypes()
        );
    }

    private List<Pokemon> searchForPokemonByWeight(List<Pokemon> pokemons, int minWeight){
        List<Pokemon> pokemonsByWeight = new ArrayList<>();
            for (Pokemon pokemon : pokemons) {
                if (pokemon.getWeight() >= minWeight) {
                    pokemonsByWeight.add(pokemon);
                }
            }
        return pokemonsByWeight;
    }

    private List<Pokemon> searchForPokemonByHeight(List<Pokemon> pokemons, int minHeight){
        List<Pokemon> pokemonsByHeight = new ArrayList<>();
            for (Pokemon pokemon : pokemons) {
                if (pokemon.getHeight() >= minHeight) {
                    pokemonsByHeight.add(pokemon);
                }
            }
        return pokemonsByHeight;
    }

    private List<Pokemon> searchForPokemonByType(List<Pokemon> pokemons, String type){
        List<Pokemon> pokemonsByType = new ArrayList<>();
            for (Pokemon pokemon : pokemons) {
                var pokemonTypes = pokemon.getPokemonTypes();
                for (PokemonType pokemonType : pokemonTypes) {
                    var pokemonTypeName = pokemonType.getType().getName();
                    if (pokemonTypeName.contains(type)) {
                        pokemonsByType.add(pokemon);
                    }
                }
            }
        return pokemonsByType;
    }

    private List<Object> getAllPokemonNamesAndUrls(String urlWithAllPokemons) {
        var allPokemonNames = (Map<String, List<Object>>) restTemplate.getForObject(urlWithAllPokemons, Object.class);
            if(allPokemonNames !=null){
                return allPokemonNames.get("results");
            }
            return null;
    }

}


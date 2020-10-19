package com.webservice.pokedex.services;

import com.webservice.pokedex.entities.Pokemon;
import com.webservice.pokedex.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@ConfigurationProperties(value = "pokemon.url", ignoreUnknownFields = false)
public class PokemonService {
    private final RestTemplate restTemplate;
    private String url;

    @Autowired
    PokemonRepository pokemonRepository;

    public PokemonService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Pokemon search(String name){
        var urlWithTitleQuery = url + name;
        var pokemon = restTemplate.getForObject(urlWithTitleQuery, Pokemon.class);
        if(pokemon == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pokemon found.");
        }
        return pokemon;
    }

    public Optional<Pokemon> findById(String id){
        return pokemonRepository.findById(id);
    }

}

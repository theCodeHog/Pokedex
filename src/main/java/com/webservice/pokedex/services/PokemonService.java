package com.webservice.pokedex.services;

import com.webservice.pokedex.entities.Pokemon;
import com.webservice.pokedex.repositories.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PokemonService {

    @Autowired
    PokemonRepository pokemonRepository;

    public Optional<Pokemon> findById(String id){
        return pokemonRepository.findById(id);
    }

}

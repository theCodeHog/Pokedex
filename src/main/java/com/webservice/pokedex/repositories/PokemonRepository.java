package com.webservice.pokedex.repositories;

import com.webservice.pokedex.entities.Pokemon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonRepository extends MongoRepository<Pokemon, String> {

    public List<Pokemon> findAllByName(String name);

}

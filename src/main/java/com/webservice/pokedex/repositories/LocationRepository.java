package com.webservice.pokedex.repositories;

import com.webservice.pokedex.entities.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends MongoRepository<Location, String> {

    public List<Location> findAllByName(String name);

}

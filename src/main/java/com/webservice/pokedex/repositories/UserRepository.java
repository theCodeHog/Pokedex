package com.webservice.pokedex.repositories;

import com.webservice.pokedex.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findById(String id);
    Optional<User> findByUsername(String username);

}

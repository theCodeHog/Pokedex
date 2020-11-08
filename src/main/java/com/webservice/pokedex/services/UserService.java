package com.webservice.pokedex.services;

import com.webservice.pokedex.entities.User;
import com.webservice.pokedex.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Could not find the user by id %s.", id)));
    }

    public User findByUsername(String username){
        var user = userRepository.findByUsername(username);
        return user.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Could not find the user by username %s.", username)));
    }

    public User save(User user){
        if(user.getPassword() == null){
            throw new ResponseStatusException(HttpStatus.I_AM_A_TEAPOT, "I need a password!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void update(String id, User userUpdate){

        var loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = loggedInUser.getAuthorities().stream().anyMatch(ga ->
                ga.getAuthority().equals("ROLE_ADMIN"));

        User userToUpdate = userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("No user found by id: %s", id)));

        //you can change someone else's username as an admin
        if(!userToUpdate.getUsername().equals(userUpdate.getUsername()) && !isAdmin) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You are not allowed here!");
        }

        userUpdate.setId(id);
        userToUpdate.setPassword(passwordEncoder.encode(userUpdate.getPassword()));
        userRepository.save(userUpdate);
    }

    public void delete(String id){
        if(!userRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Could not find the following id %s.", id));
        }
        userRepository.deleteById(id);
    }

}

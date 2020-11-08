package com.webservice.pokedex.controllers;

import com.webservice.pokedex.entities.User;
import com.webservice.pokedex.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    //USE Basic Auth - Username: admin | Password: admin123 - to access everything as admin
    //USE Basic Auth - Username: testy | Password: testy101 - to access endpoints as user

    @Autowired
    private UserService userService;

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all users", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden - You are not allowed here!", content = @Content),
            @ApiResponse(responseCode = "404", description = "No users found", content = @Content) })
    @GetMapping
    @Secured("ROLE_USER") // users can access this - not anonymous people
    public ResponseEntity<List<User>> findAllUsers(){
        var users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Get a user by their id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user by id", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden - You are not allowed here!", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)})
    @GetMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<User> findUserById(@Parameter(description = "id of user to be searched") @PathVariable String id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @Operation(summary = "Get a user by their username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user by username", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden - You are not allowed here!", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content) })
    @GetMapping("/username/{username}")
    @Secured("ROLE_USER")
    public ResponseEntity<User> findUserByUsername(@Parameter(description = "username of user to be searched") @PathVariable String username){
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @Operation(summary = "Create a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created the user succesfully.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "403", description = "Forbidden - You are not allowed here!", content = @Content),
            @ApiResponse(responseCode = "418", description = "I am a teapot. (Cannot create a user without a password)", content = @Content) })
    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<User> saveUser(@Validated @RequestBody User user) {//@Validated checks the @NOT_EMPTY and such in User Entity
        return ResponseEntity.ok(userService.save(user));
    }

    @Operation(summary = "Update a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Updated the user succesfully. No content to return.", content = @Content),
            @ApiResponse(responseCode = "401", description = "Not authorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden - You are not allowed here!", content = @Content)
    })
    @PutMapping("/{id}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@Parameter(description = "id of user to be updated") @PathVariable String id, @RequestBody User user){
        userService.update(id, user);
    }

    @Operation(summary = "Delete a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted the user succesfully. No content to return."),
            @ApiResponse(responseCode = "403", description = "Forbidden - You are not allowed here!", content = @Content)
    })
    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@Parameter(description = "id of user to be deleted")@PathVariable String id){
        userService.delete(id);
    }

}

package com.webservice.pokedex.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class User {

    @Id
    @Schema(description = "Id of the user", example = " ")
    private String id;
    @Schema(description = "Name of the user", example = "Maya")
    @NotNull(message = "Name may not be null")
    private String name;
    @Schema(description = "Username of the user", example = "Maya101")
    @NotEmpty(message = "username may not be empty.") //checks that its not null AND not empty
    private String username;
    @Schema(description = "Password of the user", example = "itsmemaya")
    @NotEmpty(message = "password may not be empty.") @Size(min = 8, max = 12)
    private String password;
    @Schema(description = "Array with strings of roles the user can have, i.e. user")
    private List<String> roles;

    public User(){}

    public User(String name, String username, String password, List<String> roles) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore //ignore to get this password when getting a user
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

}

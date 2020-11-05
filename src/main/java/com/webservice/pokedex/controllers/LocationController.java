package com.webservice.pokedex.controllers;

import com.webservice.pokedex.entities.Location;
import com.webservice.pokedex.services.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/rest/v1/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @Operation(summary = "Get a location by name or id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Location.class)) }),
            @ApiResponse(responseCode = "404", description = "Location not found",
                    content = @Content) })
    @GetMapping("/{nameOrId}")
    @Secured("ROLE_USER")
    public ResponseEntity<List<Location>> findLocationByName(@PathVariable String nameOrId){
        List<Location> locations = locationService.searchByName(nameOrId);
        return ResponseEntity.ok(locations);
    }

}

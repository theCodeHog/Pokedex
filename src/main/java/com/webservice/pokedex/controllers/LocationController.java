package com.webservice.pokedex.controllers;

import com.webservice.pokedex.entities.Item;
import com.webservice.pokedex.entities.Location;
import com.webservice.pokedex.services.ItemService;
import com.webservice.pokedex.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/v1/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    //Get location by name //or id????
    @GetMapping("/{name}")
    public ResponseEntity<List<Location>> findLocationByName(@PathVariable String name){
        List<Location> locations = (List<Location>) locationService.searchByName(name);
        return ResponseEntity.ok(locations);
    }

}

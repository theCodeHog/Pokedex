package com.webservice.pokedex.services;

import com.webservice.pokedex.dto.LocationDto;
import com.webservice.pokedex.entities.Location;
import com.webservice.pokedex.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class LocationService {

    private final RestTemplate restTemplate;
    private String url = "https://pokeapi.co/api/v2/location/";

    //constructor, setter
    public LocationService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @Autowired
    private LocationRepository locationRepository;

    public List<Location> searchByName(String name){
        var urlWithTitleQuery = url + name;

        var locations = locationRepository.findAllByName(name);

        if(locations.isEmpty()){
            var locationDto = restTemplate.getForObject(urlWithTitleQuery, LocationDto.class);
            if(locationDto == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No location found.");
            }
            else {
                var newLocation = new Location(
                        locationDto.getId(),
                        locationDto.getName(),
                        locationDto.getRegion(),
                        locationDto.getAreas()
                );
                locations.add(this.save(newLocation));
            }
        }
        return locations;
    }

    public Location save(Location location){
        return locationRepository.save(location);
    }

}

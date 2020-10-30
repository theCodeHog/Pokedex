package com.webservice.pokedex.dto;

import java.util.List;

//DTO = Data Transfer Object
public class LocationDto {

    private String id;
    private String name;
    private Object region;
    private List<Object> areas;

    public LocationDto(){}

    public LocationDto(String name, Object region, List<Object> areas) {
        this.name = name;
        this.region = region;
        this.areas = areas;
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

    public Object getRegion() {
        return region;
    }

    public void setRegion(Object region) {
        this.region = region;
    }

    public List<Object> getAreas() {
        return areas;
    }

    public void setAreas(List<Object> areas) {
        this.areas = areas;
    }
}

package com.webservice.pokedex.entities;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Location {

    @Id
    private String id;
    private String name;
    private Object region;
    private List<Object> areas;

    public Location(){}

    public Location(String id, String name, Object region, List<Object> areas) {
        this.id = id;
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

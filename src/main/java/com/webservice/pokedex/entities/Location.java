package com.webservice.pokedex.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;
import java.util.List;

public class Location {

    @Id
    @Schema(description = "Id of the location", example = "15")
    private String id;
    @Schema(description = "Name of the location", example = "oreburgh-gate")
    private String name;
    @Schema(description = "Region the location belongs to")
    private Object region;
    @Schema(description = "Areas the location contains")
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

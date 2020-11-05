package com.webservice.pokedex.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.Id;

import java.util.List;

public class Item {

    @Id
    @Schema(description = "Id of the item", example = "12")
    private String id;
    @Schema(description = "Name of the item", example = "premier-ball")
    private String name;
    @Schema(description = "The category the item belongs to - i.e. special-balls")
    private Object category;
    @Schema(description = "The cost of the item")
    private int cost;
    @Schema(description = "Attributes of the item - what you can do with it - i.e. countable, holdable")
    private List<Object> attributes;

    public Item(){}

    public Item(String id, String name, Object category, int cost, List<Object> attributes) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.cost = cost;
        this.attributes = attributes;
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

    public Object getCategory() {
        return category;
    }

    public void setCategory(Object category) {
        this.category = category;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Object> attributes) {
        this.attributes = attributes;
    }
}

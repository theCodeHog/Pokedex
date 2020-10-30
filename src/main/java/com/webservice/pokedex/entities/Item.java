package com.webservice.pokedex.entities;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Item {

    @Id
    private String id;
    private String name;
    private Object category;
    private int cost;
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

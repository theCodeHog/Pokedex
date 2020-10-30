package com.webservice.pokedex.controllers;

import com.webservice.pokedex.entities.Item;
import com.webservice.pokedex.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/v1/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    //Get item by name //or id????
    @GetMapping("/{name}")
    public ResponseEntity<List<Item>> findItemByName(@PathVariable String name){
        List<Item> items = (List<Item>) itemService.searchByName(name);
        return ResponseEntity.ok(items);
    }

}

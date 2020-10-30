package com.webservice.pokedex.services;

import com.webservice.pokedex.dto.ItemDto;
import com.webservice.pokedex.entities.Item;
import com.webservice.pokedex.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ItemService {
    private final RestTemplate restTemplate;
    private String url = "https://pokeapi.co/api/v2/item/";

    //constructor, setter
    public ItemService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> searchByName(String name){
        var urlWithTitleQuery = url + name;

        var items = itemRepository.findAllByName(name);

        if(items.isEmpty()){
            var itemDto = restTemplate.getForObject(urlWithTitleQuery, ItemDto.class);
            if(itemDto == null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No item found.");
            }
            else {
                var newItem = new Item(
                        itemDto.getId(),
                        itemDto.getName(),
                        itemDto.getCategory(),
                        itemDto.getCost(),
                        itemDto.getAttributes()
                );
                items.add(this.save(newItem));
            }
        }
        return items;
    }

    public Item save(Item item){
        return itemRepository.save(item);
    }




}

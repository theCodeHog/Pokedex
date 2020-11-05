package com.webservice.pokedex.controllers;

import com.webservice.pokedex.entities.Item;
import com.webservice.pokedex.services.ItemService;
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
@RequestMapping("/rest/v1/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Operation(summary = "Get an item by name or id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Item.class)) }),
            @ApiResponse(responseCode = "404", description = "Item not found",
                    content = @Content) })
    @GetMapping("/{nameOrId}")
    @Secured("ROLE_USER")
    public ResponseEntity<List<Item>> findItemByName(@PathVariable String nameOrId){
        List<Item> items = itemService.searchByNameOrId(nameOrId);
        return ResponseEntity.ok(items);
    }

}

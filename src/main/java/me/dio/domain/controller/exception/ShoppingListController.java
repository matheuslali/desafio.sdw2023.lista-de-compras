package me.dio.domain.controller.exception;


import me.dio.domain.model.ShoppingList;
import me.dio.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(name = "/shopping-list")
public class ShoppingListController {

    @Autowired
    private ShoppingListService shoppingListService;


    @GetMapping("/shopping-list")
    public ResponseEntity<List<ShoppingList>> findAllShoppingList(){
        var shoppingLists = shoppingListService.findAll();
        return ResponseEntity.ok(shoppingLists);
    }

    @GetMapping("/shopping-list/{id}")
    public ResponseEntity<ShoppingList> findByIdShoppingList(@PathVariable Long id){
        var shoppingLists = shoppingListService.findById(id);
        return ResponseEntity.ok(shoppingLists);
    }

    @PostMapping("/shopping-list")
    public ResponseEntity<ShoppingList> createShoppingList(@RequestBody ShoppingList shoppingListCreate){
        shoppingListService.create(shoppingListCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(shoppingListCreate.getId())
                .toUri();
        return ResponseEntity.created(location).body(shoppingListCreate);
    }

    @PutMapping("/shopping-list/{id}")
    public ResponseEntity<ShoppingList> updateShoppingList(@PathVariable Long id,@RequestBody ShoppingList shoppingLists){
        var shoppingListUpdate = shoppingListService.update(id, shoppingLists);
        return ResponseEntity.ok(shoppingListUpdate);
    }

    @DeleteMapping("/shopping-list/{id}")
    public ResponseEntity<Void> deleteShoppingList(@PathVariable Long id){
        shoppingListService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

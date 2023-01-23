package talgat.demo.store.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import talgat.demo.store.back.models.ItemOrderDto;
import talgat.demo.store.back.models.ItemStore;
import talgat.demo.store.back.models.ItemStoreDto;
import talgat.demo.store.back.services.ItemStoreService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins = {"http://localhost:3870", "http://localhost:8081", "http://localhost:8082"})
public class ItemStoreController {
    private ItemStoreService itemStoreService;
    @Autowired
    public ItemStoreController(ItemStoreService itemStoreService) {
        this.itemStoreService = itemStoreService;
    }

    @GetMapping(path = "api/store/items/find", params = "all")
    public Iterable<ItemStoreDto> findAllItems(){
        return itemStoreService.findAllItems();
    }

    @GetMapping(path = "api/store/items/find-by-ids")
    public Iterable<ItemStoreDto> findItemsByIds(@RequestParam List<Long> ids){
        return itemStoreService.findItemsByIds(ids);
    }
    @GetMapping(path = "api/store/items/find-by-name")
    public ResponseEntity<ItemStoreDto> findItemByName(@RequestParam String name){
        return itemStoreService.findItemByName(name);
    }

    @GetMapping(path = "api/store/items/find-by-id")
    public ResponseEntity<ItemStoreDto> findItemById(@RequestParam Long id){
        return itemStoreService.findItemById(id);
    }

    @PostMapping(path = "api/store/items/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createItem(@RequestBody ItemStoreDto itemStoreDto){
        itemStoreService.createItem(itemStoreDto);
    }

}

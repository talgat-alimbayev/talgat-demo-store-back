package talgat.demo.store.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import talgat.demo.store.back.models.ItemStore;
import talgat.demo.store.back.services.ItemStoreService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/items",
        produces = "application/json")
@CrossOrigin(origins = {"http://localhost:3870", "http://localhost:8081", "http://localhost:8082"})
public class ItemController {
    private ItemStoreService itemStoreService;
    @Autowired
    public ItemController(ItemStoreService itemStoreService) {
        this.itemStoreService = itemStoreService;
    }

    @GetMapping(params = "all")
    public Iterable<ItemStore> findAllItems(){
        return itemStoreService.findAllItems();
    }

    @GetMapping(path = "api/store/items")
    public Iterable<ItemStore> findItemsByIds(@RequestParam List<Long> ids){
        return itemStoreService.findItemsByIds(ids);
    }

    @PostMapping
    public ItemStore createItem(@RequestBody ItemStore itemStore){
        return itemStoreService.createItem(itemStore);
    }

//    @PostMapping
//    public ResponseEntity<Item> findItemById(@RequestParam Long id){
//        Optional<Item> itemOptional = itemService.findItemByIds(id);
//
//        if (itemOptional.isPresent()){
//            return new ResponseEntity<>(itemOptional.get(), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//    }
}

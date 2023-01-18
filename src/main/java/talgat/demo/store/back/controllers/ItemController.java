package talgat.demo.store.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import talgat.demo.store.back.models.Item;
import talgat.demo.store.back.repositories.ItemRepository;

import java.util.List;

@RestController
@RequestMapping(path = "/api/items",
        produces = "application/json")
@CrossOrigin(origins = {"http://localhost:3870", "http://localhost:8081", "http://localhost:8082"})
public class ItemController {
    private ItemRepository itemRepo;
    @Autowired
    public ItemController(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    @GetMapping(params = "all")
    public Iterable<Item> findAllItems(){
        return itemRepo.findAll();
    }

    @GetMapping
    public Iterable<Item> findItemsByIds(@RequestParam List<Long> ids){
        return itemRepo.findAllById(ids);
    }

    @PostMapping
    public Item createItem(@RequestBody Item item){
        return itemRepo.save(item);
    }
}

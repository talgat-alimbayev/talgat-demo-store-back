package talgat.demo.store.back.controllers;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import talgat.demo.store.back.models.Item;
import talgat.demo.store.back.repositories.ItemRepository;

@RestController
@RequestMapping(path = "/api/items",
        produces = "application/json")
@CrossOrigin(origins = "*")
public class ItemController {
    private ItemRepository itemRepo;

    public ItemController(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    @GetMapping(params = "all")
    public Flux<Item> findAllItems(){
        return itemRepo.findAll();
    }

    @GetMapping
    public Mono<Item> findItemById(@RequestParam Long id){
        return itemRepo.findById(id);
    }

}

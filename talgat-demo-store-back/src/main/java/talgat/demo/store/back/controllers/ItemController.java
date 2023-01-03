package talgat.demo.store.back.controllers;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import talgat.demo.store.back.models.Item;
import talgat.demo.store.back.repositories.ItemRepository;

import java.util.List;

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

//    @GetMapping
//    public Mono<Item> findItemById(@RequestParam Long id){
//        return itemRepo.findById(id);
//    }

    @GetMapping
    public Flux<Item> findItemsByIds(@RequestParam List<Long> ids){
        return itemRepo.findAllById(ids);
    }

    @PostMapping
    public Mono<Item> createItem(@RequestBody Item item){
        return itemRepo.save(item);
    }
}

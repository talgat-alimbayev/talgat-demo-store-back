package talgat.demo.store.back.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
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
}

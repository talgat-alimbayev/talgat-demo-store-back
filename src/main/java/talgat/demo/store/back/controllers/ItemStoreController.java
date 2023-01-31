package talgat.demo.store.back.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import talgat.demo.store.back.models.ItemStoreDTO;
import talgat.demo.store.back.services.ItemStoreService;

import java.util.List;

@RestController
@RequestMapping(produces = "application/json")
@Slf4j
public class ItemStoreController {
    private ItemStoreService itemStoreService;
    @Autowired
    public ItemStoreController(ItemStoreService itemStoreService) {
        this.itemStoreService = itemStoreService;
    }

    @GetMapping(path = "api/store/items/find", params = "all")
    public Iterable<ItemStoreDTO> findAllItems(){
        log.info("fetching all the store items");
        return itemStoreService.findAllItems();
    }

    @GetMapping(path = "api/store/items/find-by-ids")
    public Iterable<ItemStoreDTO> findItemsByIds(@RequestParam List<Long> ids){
        log.info("fetching store items from a list of ids=" + ids.toString());
        return itemStoreService.findItemsByIds(ids);
    }
    @GetMapping(path = "api/store/items/find-by-name")
    public ResponseEntity<ItemStoreDTO> findItemByName(@RequestParam String name){
        log.info("fetching one store item by its name=" + name);
        return itemStoreService.findItemByName(name);
    }

    @GetMapping(path = "api/store/items/find-by-id")
    public ResponseEntity<ItemStoreDTO> findItemById(@RequestParam Long id){
        log.info("fetching one store item by its id=" + id);
        return itemStoreService.findItemById(id);
    }

    @PostMapping(path = "api/store/items/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createItem(@RequestBody @Valid ItemStoreDTO itemStoreDto){
        log.info("saving one store item to the DB. Item=" + itemStoreDto.toString());
        itemStoreService.createItem(itemStoreDto);
    }

}

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

//    @GetMapping(path = "api/store/items", params = "all")
//    public Iterable<ItemStoreDto> findAllItems(){
//        List<ItemStoreDto> items = new ArrayList<>();
//        itemStoreService.findAllItems().forEach(itemStore -> items.add(new ItemStoreDto(itemStore)));
//        return items;
//    }

//    @GetMapping(path = "api/store/items")
//    public Iterable<ItemStoreDto> findItemsByIds(@RequestParam List<Long> ids){
//        List<ItemStoreDto> items = new ArrayList<>();
//        itemStoreService.findItemsByIds(ids).forEach(itemStore -> items.add(new ItemStoreDto(itemStore)));
//        return items;
//    }

//    @PostMapping(path = "api/store/items/create")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void createItem(@RequestBody ItemStore itemStore){
//        itemStoreService.createItem(itemStore);
//    }

//    @PostMapping(path = "api/store/items")
//    public ResponseEntity<ItemStoreDto> findItemById(@RequestParam Long id){
//        Optional<ItemStore> itemOptional = itemStoreService.findItemByIds(id);
//
//        if (itemOptional.isPresent()){
//            ItemStoreDto itemOrderDto = new ItemStoreDto(itemOptional.get());
//            return new ResponseEntity<>(itemOrderDto, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//    }
}

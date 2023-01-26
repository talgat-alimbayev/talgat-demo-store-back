package talgat.demo.store.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import talgat.demo.store.back.models.ItemStore;
import talgat.demo.store.back.models.ItemStoreDto;
import talgat.demo.store.back.repositories.ItemStoreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemStoreService {
    private ItemStoreRepository itemStoreRepo;
    @Autowired
    public ItemStoreService(ItemStoreRepository itemStoreRepo) {
        this.itemStoreRepo = itemStoreRepo;
    }

    public Iterable<ItemStoreDto> findAllItems(){
        List<ItemStoreDto> items = new ArrayList<>();
        itemStoreRepo.findAll().forEach(itemStore -> items.add(new ItemStoreDto(itemStore)));
        return items;
    }

    public Iterable<ItemStoreDto> findItemsByIds(List<Long> ids){
        List<ItemStoreDto> items = new ArrayList<>();
        itemStoreRepo.findAllById(ids).forEach(itemStore -> items.add(new ItemStoreDto(itemStore)));
        return items;
    }

    public Iterable<ItemStore> findItemsByName(List<String> names){
        return itemStoreRepo.findByNameIn(names);
    }

    public ResponseEntity<ItemStoreDto> findItemById(Long id){
        Optional<ItemStore> itemOptional = itemStoreRepo.findById(id);
        if (itemOptional.isPresent()){
            ItemStoreDto itemOrderDto = new ItemStoreDto(itemOptional.get());
            return new ResponseEntity<>(itemOrderDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ItemStoreDto> findItemByName(String name){
        Optional<ItemStore> itemOptional =  itemStoreRepo.findByName(name);
        if (itemOptional.isPresent()){
            ItemStoreDto itemStoreDto = new ItemStoreDto(itemOptional.get());
            return new ResponseEntity<>(itemStoreDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    public ItemStoreDto createItem(ItemStoreDto itemStoreDto){
        ItemStore itemStore = new ItemStore(itemStoreDto);
        return new ItemStoreDto(itemStoreRepo.save(itemStore));
    }
}

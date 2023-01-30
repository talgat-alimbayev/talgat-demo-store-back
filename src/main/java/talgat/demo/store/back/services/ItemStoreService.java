package talgat.demo.store.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import talgat.demo.store.back.models.ItemStore;
import talgat.demo.store.back.models.ItemStoreDTO;
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

    public Iterable<ItemStoreDTO> findAllItems(){
        List<ItemStoreDTO> items = new ArrayList<>();
        itemStoreRepo.findAll().forEach(itemStore -> items.add(new ItemStoreDTO(itemStore)));
        return items;
    }

    public Iterable<ItemStoreDTO> findItemsByIds(List<Long> ids){
        List<ItemStoreDTO> items = new ArrayList<>();
        itemStoreRepo.findAllById(ids).forEach(itemStore -> items.add(new ItemStoreDTO(itemStore)));
        return items;
    }

    public Iterable<ItemStore> findItemsByName(List<String> names){
        return itemStoreRepo.findByNameIn(names);
    }

    public ResponseEntity<ItemStoreDTO> findItemById(Long id){
        Optional<ItemStore> itemOptional = itemStoreRepo.findById(id);
        if (itemOptional.isPresent()){
            ItemStoreDTO itemOrderDto = new ItemStoreDTO(itemOptional.get());
            return new ResponseEntity<>(itemOrderDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<ItemStoreDTO> findItemByName(String name){
        Optional<ItemStore> itemOptional =  itemStoreRepo.findByName(name);
        if (itemOptional.isPresent()){
            ItemStoreDTO itemStoreDto = new ItemStoreDTO(itemOptional.get());
            return new ResponseEntity<>(itemStoreDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    public ItemStoreDTO createItem(ItemStoreDTO itemStoreDto){
        ItemStore itemStore = new ItemStore(itemStoreDto);
        return new ItemStoreDTO(itemStoreRepo.save(itemStore));
    }
}

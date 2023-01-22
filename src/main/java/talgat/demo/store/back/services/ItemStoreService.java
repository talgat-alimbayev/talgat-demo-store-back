package talgat.demo.store.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import talgat.demo.store.back.models.ItemStore;
import talgat.demo.store.back.repositories.ItemStoreRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ItemStoreService {
    private ItemStoreRepository itemStoreRepo;
    @Autowired
    public ItemStoreService(ItemStoreRepository itemStoreRepo) {
        this.itemStoreRepo = itemStoreRepo;
    }

    public Iterable<ItemStore> findAllItems(){
        return itemStoreRepo.findAll();
    }

    public Iterable<ItemStore> findItemsByIds(List<Long> ids){
        return itemStoreRepo.findAllById(ids);
    }

    public Iterable<ItemStore> findItemsByName(List<String> names){
        return itemStoreRepo.findByNameIn(names);
    }

    public Optional<ItemStore> findItemById(Long id){
        return itemStoreRepo.findById(id);
    }

    public Optional<ItemStore> findItemByName(String name){
        return itemStoreRepo.findByName(name);
    }
    public ItemStore createItem(ItemStore item){
        return itemStoreRepo.save(item);
    }
}

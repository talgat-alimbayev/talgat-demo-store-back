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

    public Optional<ItemStore> findItemByIds(Long id){
        return itemStoreRepo.findById(id);
    }

    public ItemStore createItem(ItemStore item){
        return itemStoreRepo.save(item);
    }
}

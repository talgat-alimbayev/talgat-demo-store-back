package talgat.demo.store.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import talgat.demo.store.back.models.ItemOrder;
import talgat.demo.store.back.repositories.ItemOrderRepository;

import java.util.Optional;

@Service
public class ItemOrderService {
    private ItemOrderRepository itemOrderRepo;

    @Autowired
    public ItemOrderService(ItemOrderRepository itemOrderRepo) {
        this.itemOrderRepo = itemOrderRepo;
    }

    public Iterable<ItemOrder> findItemsByOrder(Long id){
        return itemOrderRepo.findByOrderId(id);
    }
}

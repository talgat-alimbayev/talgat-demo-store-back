package talgat.demo.store.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import talgat.demo.store.back.models.ItemOrderDTO;
import talgat.demo.store.back.repositories.ItemOrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemOrderService {
    private ItemOrderRepository itemOrderRepo;

    @Autowired
    public ItemOrderService(ItemOrderRepository itemOrderRepo) {
        this.itemOrderRepo = itemOrderRepo;
    }

    public Iterable<ItemOrderDTO> findItemsByOrder(Long id){
        List<ItemOrderDTO> items = new ArrayList<>();
        itemOrderRepo.findByOrderId(id).forEach(itemOrder -> items.add(new ItemOrderDTO(itemOrder)));
        return items;
    }
}

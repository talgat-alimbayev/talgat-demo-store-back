package talgat.demo.store.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import talgat.demo.store.back.models.ItemOrderDto;
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

    public Iterable<ItemOrderDto> findItemsByOrder(Long id){
        List<ItemOrderDto> items = new ArrayList<>();
        itemOrderRepo.findByOrderId(id).forEach(itemOrder -> items.add(new ItemOrderDto(itemOrder)));
        return items;
    }
}

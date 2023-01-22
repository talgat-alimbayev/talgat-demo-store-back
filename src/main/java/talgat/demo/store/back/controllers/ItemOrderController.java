package talgat.demo.store.back.controllers;

import org.springframework.web.bind.annotation.*;
import talgat.demo.store.back.models.ItemOrderDto;
import talgat.demo.store.back.services.ItemOrderService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins = {"http://localhost:3870", "http://localhost:8081", "http://localhost:8082"})
public class ItemOrderController {
    private ItemOrderService itemOrderService;

    public ItemOrderController(ItemOrderService itemOrderService) {
        this.itemOrderService = itemOrderService;
    }

    @GetMapping(path = "api/orders/items/find-by-orderid")
    public Iterable<ItemOrderDto> findItemsByIds(@RequestParam Long orderId){
        List<ItemOrderDto> items = new ArrayList<>();
        itemOrderService.findItemsByOrder(orderId).forEach(itemOrder -> items.add(new ItemOrderDto(itemOrder)));
        return items;
    }
}

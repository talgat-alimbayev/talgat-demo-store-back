package talgat.demo.store.back.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import talgat.demo.store.back.models.ItemOrderDTO;
import talgat.demo.store.back.services.ItemOrderService;

@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins = {"http://localhost:3870", "http://localhost:8081", "http://localhost:8082"})
@Slf4j
public class ItemOrderController {
    private ItemOrderService itemOrderService;

    public ItemOrderController(ItemOrderService itemOrderService) {
        this.itemOrderService = itemOrderService;
    }

    @GetMapping(path = "api/orders/items/find-by-orderid")
    public Iterable<ItemOrderDTO> findItemsByOrder(@RequestParam Long orderId){
        log.info("searching for order items by orderId = " + orderId.toString());
        return itemOrderService.findItemsByOrder(orderId);
    }
}

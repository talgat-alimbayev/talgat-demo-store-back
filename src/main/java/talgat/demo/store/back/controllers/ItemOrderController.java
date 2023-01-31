package talgat.demo.store.back.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import talgat.demo.store.back.models.ItemOrderDTO;
import talgat.demo.store.back.services.ItemOrderService;

@RestController
@RequestMapping(produces = "application/json")
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

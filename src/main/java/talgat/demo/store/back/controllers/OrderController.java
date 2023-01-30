package talgat.demo.store.back.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import talgat.demo.store.back.models.OrderCompleteDTO;
import talgat.demo.store.back.models.OrderDTO;
import talgat.demo.store.back.services.OrderService;

@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins = "*")
@Slf4j
public class OrderController {
    private OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(path = "api/orders/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrderDTO> saveOrder(@RequestBody @Valid OrderCompleteDTO orderCompleteDTO){
        log.info("saving an order:" + orderCompleteDTO.toString());
        return orderService.saveOrder(orderCompleteDTO);
    }

    @GetMapping(path = "api/orders/find", params = "all")
    public Iterable<OrderDTO> findAllOrders(){
        log.info("fetching all saved orderes");
        return orderService.findAllOrders();
    }

    @GetMapping(path = "api/orders/find-by-userid")
    public Iterable<OrderDTO> findOrdersByUserid(@RequestParam Long userid){
        log.info("fetching an order by userId=" + userid.toString());
        return orderService.findByUserId(userid);
    }
}

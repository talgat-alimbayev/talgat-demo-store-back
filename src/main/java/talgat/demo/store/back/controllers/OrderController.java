package talgat.demo.store.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import talgat.demo.store.back.models.OrderDto;
import talgat.demo.store.back.services.OrderService;
import talgat.demo.store.back.services.email.OrderEmailService;

@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins = "*")
public class OrderController {
    private OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(path = "api/orders/save", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrderDto> saveOrder(@RequestBody OrderDto orderDto){
        return orderService.saveOrder(orderDto);
    }

    @GetMapping(path = "api/orders/find", params = "all")
    public Iterable<OrderDto> findAllOrders(){
        return orderService.findAllOrders();
    }

    @GetMapping(path = "api/orders/find-by-userid")
    public Iterable<OrderDto> findOrderByUserid(@RequestParam Long userid){
        return orderService.findByUserId(userid);
    }
}

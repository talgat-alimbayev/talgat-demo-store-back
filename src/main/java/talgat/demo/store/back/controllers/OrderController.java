package talgat.demo.store.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import talgat.demo.store.back.models.Order;
import talgat.demo.store.back.models.OrderDto;
import talgat.demo.store.back.repositories.OrderRepository;
import talgat.demo.store.back.services.OrderService;

@RestController
@RequestMapping(produces = "application/json")
@CrossOrigin(origins = "*")
public class OrderController {
    private OrderService orderService;
    @Autowired
    public OrderController(OrderRepository orderRepo, OrderService orderService) {
        this.orderService = orderService;
    }

//    @PostMapping(consumes = "application/json")
//    @ResponseStatus(HttpStatus.CREATED)
//    public OrderDto saveOrder(@RequestBody OrderDto orderDto){
//        return orderService.saveOrder(orderDto);
//    }

    @GetMapping(path = "api/orders/find", params = "all")
    public Iterable<OrderDto> findAllOrders(){
        return orderService.findAllOrders();
    }

}

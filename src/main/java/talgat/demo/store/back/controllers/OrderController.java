package talgat.demo.store.back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import talgat.demo.store.back.models.Order;
import talgat.demo.store.back.repositories.OrderRepository;

@RestController
@RequestMapping(path = "/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {
    private OrderRepository orderRepo;
    @Autowired
    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Order postOrder(@RequestBody Order order){
        return orderRepo.save(order);
    }

    @GetMapping(params = "all",
            produces = "application/json")
    public Iterable<Order> findAllOrders(){
        return orderRepo.findAll();
    }

}

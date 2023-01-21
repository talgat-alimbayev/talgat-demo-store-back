package talgat.demo.store.back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import talgat.demo.store.back.models.Order;
import talgat.demo.store.back.models.OrderDto;
import talgat.demo.store.back.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepo;


    public OrderService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    public Order saveOrder(Order order){
        return orderRepo.save(order);
    }

    public Iterable<OrderDto> findAllOrders(){
        Iterable<Order> orders = orderRepo.findAll();
        List<OrderDto> ordersDto = new ArrayList<OrderDto>();
        orders.forEach(order -> {ordersDto.add(new OrderDto(order)); } );
        return ordersDto;
    }

}

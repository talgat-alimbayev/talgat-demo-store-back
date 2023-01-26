package talgat.demo.store.back.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import talgat.demo.store.back.models.*;
import talgat.demo.store.back.repositories.ItemOrderRepository;
import talgat.demo.store.back.repositories.OrderRepository;
import talgat.demo.store.back.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService {
    private OrderRepository orderRepo;
    private UserRepository userRepo;
    private ItemOrderRepository itemOrderRepo;

    public OrderService(OrderRepository orderRepo, UserRepository userRepo, ItemOrderRepository itemOrderRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.itemOrderRepo = itemOrderRepo;
    }
    @Transactional
    public ResponseEntity<OrderDto> saveOrder(OrderDto orderDto){
        Optional<User> userOptional = userRepo.findById(orderDto.getUserId());
//        log.info(userOptional.get().getId().toString());
        if (userOptional.isPresent()){
            User user = userOptional.get();

            Order order = new Order(orderDto);
            order.setUser(user);
            orderRepo.save(order);

            List<ItemOrderDto> itemsOrderDto = orderDto.getItems();
            List<ItemOrder> itemsOrder = new ArrayList<>();
            itemsOrderDto.forEach(itemOrderDto -> {
                itemsOrder.add(new ItemOrder(itemOrderDto));
                    });
            itemsOrder.forEach(itemOrder -> itemOrder.setOrder(order));
            itemOrderRepo.saveAll(itemsOrder);

            return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public Iterable<OrderDto> findAllOrders(){
        Iterable<Order> orders = orderRepo.findAll();
        List<OrderDto> ordersDto = new ArrayList<OrderDto>();
        orders.forEach(order -> {
            ordersDto.add(new OrderDto(order));
        });
        return ordersDto;
    }

    public Iterable<OrderDto> findByUserId(Long userId){
        Iterable<Order> orders = orderRepo.findByUser(userId);
        List<OrderDto> ordersDto = new ArrayList<>();
        orders.forEach(order -> {ordersDto.add(new OrderDto(order)); } );
        return ordersDto;
    }
}

package talgat.demo.store.back.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import talgat.demo.store.back.models.*;
import talgat.demo.store.back.repositories.ItemOrderRepository;
import talgat.demo.store.back.repositories.OrderRepository;
import talgat.demo.store.back.repositories.UserRepository;
import talgat.demo.store.back.services.email.OrderEmailService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository orderRepo;
    private UserRepository userRepo;
    private ItemOrderRepository itemOrderRepo;
    private OrderEmailService orderEmailService;

    public OrderService(OrderRepository orderRepo, UserRepository userRepo, ItemOrderRepository itemOrderRepo, OrderEmailService orderEmailService) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.itemOrderRepo = itemOrderRepo;
        this.orderEmailService = orderEmailService;
    }
    @Transactional
    public ResponseEntity<OrderDTO> saveOrder(OrderCompleteDTO orderCompleteDto){
        Optional<User> userOptional = userRepo.findById(orderCompleteDto.getUserId());
        if (userOptional.isPresent()){
            User user = userOptional.get();

            OrderDTO orderDto = new OrderDTO(orderCompleteDto);

            Order order = new Order(orderDto);
            order.setUser(user);
            orderRepo.save(order);

            List<ItemOrderDTO> itemsOrderDto = orderCompleteDto.getItems();
            List<ItemOrder> itemsOrder = new ArrayList<>();
            itemsOrderDto.forEach(itemOrderDto -> {
                itemsOrder.add(new ItemOrder(itemOrderDto));
                    });
            itemsOrder.forEach(itemOrder -> itemOrder.setOrder(order));
            itemOrderRepo.saveAll(itemsOrder);

            orderEmailService.sendOrderEmail(orderDto);

            return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public Iterable<OrderDTO> findAllOrders(){
        Iterable<Order> orders = orderRepo.findAll();
        List<OrderDTO> ordersDto = new ArrayList<OrderDTO>();
        orders.forEach(order -> {
            ordersDto.add(new OrderDTO(order));
        });
        return ordersDto;
    }

    public Iterable<OrderDTO> findByUserId(Long userId){
        Iterable<Order> orders = orderRepo.findByUser(userId);
        List<OrderDTO> ordersDto = new ArrayList<>();
        orders.forEach(order -> {
            ordersDto.add(new OrderDTO(order));
        } );
        return ordersDto;
    }
}

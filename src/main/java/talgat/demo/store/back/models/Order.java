package talgat.demo.store.back.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String deliveryAddress;
    private String deliveryName;
    private String email;
    private String comment;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    public Order(OrderDto orderDto){
        this.id = orderDto.getId();
        this.deliveryAddress = orderDto.getDeliveryAddress();
        this.deliveryName = orderDto.getDeliveryName();
        this.email = orderDto.getEmail();
        this.comment = orderDto.getComment();
    }
}

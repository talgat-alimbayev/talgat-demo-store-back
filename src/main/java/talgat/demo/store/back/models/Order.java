package talgat.demo.store.back.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public Order(OrderDTO orderDto){
        this.id = orderDto.getId();
        this.deliveryAddress = orderDto.getDeliveryAddress();
        this.deliveryName = orderDto.getDeliveryName();
        this.email = orderDto.getEmail();
        this.comment = orderDto.getComment();
    }
}

package talgat.demo.store.back.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String deliveryAddress;
    private String deliveryName;
    private String email;
    private List<ItemOrderDto> items = new ArrayList<>();
    private String comment;
    private Long userId;

    public OrderDto(Order order){
        this.id = order.getId();
        this.deliveryAddress = order.getDeliveryAddress();
        this.deliveryName = order.getDeliveryName();
        this.email = order.getEmail();
        this.comment = order.getComment();
        this.userId = order.getUser().getId();
    }
}

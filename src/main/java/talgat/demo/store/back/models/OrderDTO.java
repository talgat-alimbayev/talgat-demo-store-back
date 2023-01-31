package talgat.demo.store.back.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    @NotBlank(message = "Не забудьте адрес доставки")
    private String deliveryAddress;
    @NotBlank(message = "Кому доставить?")
    private String deliveryName;
    @NotBlank(message = "Имейл обязателен!")
    private String email;
    private String comment;
    @Min(value = 1, message = "id пользователя обязателен")
    @NotNull(message = "id пользователя обязателен")
    private Long userId;

    public OrderDTO(Order order){
        this.id = order.getId();
        this.deliveryAddress = order.getDeliveryAddress();
        this.deliveryName = order.getDeliveryName();
        this.email = order.getEmail();
        this.comment = order.getComment();
        this.userId = order.getUser().getId();
    }

    public OrderDTO(OrderCompleteDTO orderCompleteDTO){
        this.id = orderCompleteDTO.getId();
        this.deliveryAddress = orderCompleteDTO.getDeliveryAddress();
        this.deliveryName = orderCompleteDTO.getDeliveryName();
        this.email = orderCompleteDTO.getEmail();
        this.comment = orderCompleteDTO.getComment();
        this.userId = orderCompleteDTO.getUserId();
    }
}

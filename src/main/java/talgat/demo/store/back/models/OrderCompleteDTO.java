package talgat.demo.store.back.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderCompleteDTO {
    private Long id;
    @NotBlank(message = "Не забудьте адрес доставки")
    private String deliveryAddress;
    @NotBlank(message = "Кому доставить?")
    private String deliveryName;
    @NotBlank(message = "Имейл обязателен!")
    private String email;
    @NotEmpty(message = "пустой заказ!")
    @Valid
    private List<ItemOrderDTO> items = new ArrayList<>();
    private String comment;
    @Min(value = 1, message = "id пользователя обязателен")
    @NotNull(message = "id пользователя обязателен")
    private Long userId;

    public OrderCompleteDTO(Order order){
        this.id = order.getId();
        this.deliveryAddress = order.getDeliveryAddress();
        this.deliveryName = order.getDeliveryName();
        this.email = order.getEmail();
        this.comment = order.getComment();
        this.userId = order.getUser().getId();
    }
}

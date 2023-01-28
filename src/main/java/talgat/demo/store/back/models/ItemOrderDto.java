package talgat.demo.store.back.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ItemOrderDto extends ItemDto {

    public ItemOrderDto(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
    public ItemOrderDto(ItemAbstract itemAbstract) {
        this.name = itemAbstract.getName();
        this.price = itemAbstract.getPrice();
    }
}

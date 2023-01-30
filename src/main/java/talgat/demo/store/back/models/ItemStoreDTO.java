package talgat.demo.store.back.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ItemStoreDTO extends ItemDTO {
    private Long id;
    public ItemStoreDTO(Long id, String name, BigDecimal price){
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public ItemStoreDTO(ItemAbstract itemAbstract) {
        this.id = itemAbstract.getId();
        this.name = itemAbstract.getName();
        this.price = itemAbstract.getPrice();
    }
}

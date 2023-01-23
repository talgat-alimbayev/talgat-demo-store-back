package talgat.demo.store.back.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ItemStoreDto extends ItemDto {
    public ItemStoreDto(ItemAbstract itemAbstract) {
        this.id = itemAbstract.getId();
        this.name = itemAbstract.getName();
        this.price = itemAbstract.getPrice();
    }
}

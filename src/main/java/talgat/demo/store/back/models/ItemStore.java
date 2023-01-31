package talgat.demo.store.back.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "items_store")
@NoArgsConstructor
@Data
public class ItemStore extends ItemAbstract {

    public ItemStore(Long id, String name, BigDecimal price){
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public ItemStore(ItemStoreDTO itemStoreDto){
        this.name = itemStoreDto.getName();
        this.price = itemStoreDto.getPrice();
    }
}

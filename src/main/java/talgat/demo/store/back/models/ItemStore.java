package talgat.demo.store.back.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "items_store")
@NoArgsConstructor
@Data
public class ItemStore extends ItemAbstract {

    public ItemStore(ItemStoreDto itemStoreDto){
        this.name = itemStoreDto.getName();
        this.price = itemStoreDto.getPrice();
    }
}

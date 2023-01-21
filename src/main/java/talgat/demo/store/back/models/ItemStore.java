package talgat.demo.store.back.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;

@Entity
@Table(name = "items_store")
@NoArgsConstructor
@Data
public class ItemStore extends Item{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    protected Long id;
//    @NonNull
//    protected String name;
//    @NonNull
//    protected BigDecimal price;
    public ItemStore(ItemStoreDto itemStoreDto){
        this.name = itemStoreDto.getName();
        this.price = itemStoreDto.getPrice();
    }
}

package talgat.demo.store.back.models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "items_order")
@NoArgsConstructor
@Setter
public class ItemOrder extends ItemAbstract {
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "order_id")
    private Order order;
    public ItemOrder(ItemDto itemDto){
        this.name = itemDto.getName();
        this.price = itemDto.getPrice();
    }
}

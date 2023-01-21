package talgat.demo.store.back.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "items_order")
@NoArgsConstructor
@Setter
public class ItemOrder extends Item{
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    public ItemOrder(ItemDto itemDto){
        this.name = itemDto.getName();
        this.price = itemDto.getPrice();
    }
}

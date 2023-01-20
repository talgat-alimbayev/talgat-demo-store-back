package talgat.demo.store.back.models;

import jakarta.persistence.Entity;

@Entity(name = "items_order")
public class ItemOrder extends Item{
    public ItemOrder(ItemDto itemDto){
        this.id = itemDto.getId();
        this.name = itemDto.getName();
        this.price = itemDto.getPrice();
    }
}

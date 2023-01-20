package talgat.demo.store.back.models;

import jakarta.persistence.Entity;

@Entity(name = "items_store")
public class ItemStore extends Item{
    public ItemStore(ItemDto itemDto){
        this.id = itemDto.getId();
        this.name = itemDto.getName();
        this.price = itemDto.getPrice();
    }
}

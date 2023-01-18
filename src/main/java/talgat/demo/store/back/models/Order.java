package talgat.demo.store.back.models;

import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "orders")
public class Order {
    @Id
    private Long id;
    private String deliveryAddress;
    private String deliveryName;
    private String email;
    private Set<Long> itemIds = new HashSet<>();
    private BigDecimal orderTotal;
    private String comment;
    public void addItem(Item item){
        itemIds.add(item.getId());
    }

}

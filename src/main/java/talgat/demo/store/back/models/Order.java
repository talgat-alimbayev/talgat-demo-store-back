package talgat.demo.store.back.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

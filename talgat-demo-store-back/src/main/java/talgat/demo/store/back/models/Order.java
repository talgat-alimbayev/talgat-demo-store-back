package talgat.demo.store.back.models;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
public class Order {
    @Id
    private Long id;
    @NonNull
    private String deliveryAddress;
    @NonNull
    private Set<Long> itemIds = new HashSet<>();
//    @NonNull
//    private BigDecimal orderTotal;

    public void addItem(Item item){
        itemIds.add(item.getId());
    }
}

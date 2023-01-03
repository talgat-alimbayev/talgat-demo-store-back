package talgat.demo.store.back.models;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
@Data
@Table("items")
public class Item {
    @Id
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private BigDecimal price;
}

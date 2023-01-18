package talgat.demo.store.back.models;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
@Data
@Entity(name = "items")
public class Item {
    @Id
    private Long id;
    @NonNull
    private String name;
    @NonNull
    private BigDecimal price;
}

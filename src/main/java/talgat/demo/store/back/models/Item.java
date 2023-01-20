package talgat.demo.store.back.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;
@MappedSuperclass
@Data
@NoArgsConstructor
abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @NonNull
    protected String name;
    @NonNull
    protected BigDecimal price;
}

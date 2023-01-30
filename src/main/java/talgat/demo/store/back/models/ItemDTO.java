package talgat.demo.store.back.models;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
@NoArgsConstructor
@Data
public abstract class ItemDTO {
    @Size(min = 3, message = "Слишком короткое имя продукта")
    protected String name;
    @DecimalMin(value = "0", message = "Цена не может быть ниже нуля")
    protected BigDecimal price;
}

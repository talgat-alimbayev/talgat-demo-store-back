package talgat.demo.store.back.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
@NoArgsConstructor
@Data
public abstract class ItemDto {
//    protected Long id;
    protected String name;
    protected BigDecimal price;

}

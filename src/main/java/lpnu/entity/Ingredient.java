package lpnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    @NotNull
    private String name;
    @NotNull
    private int weight;
    @NotNull
    private BigDecimal price;
    private Long id;
}
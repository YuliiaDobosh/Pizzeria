package lpnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    @NotNull
    private String name;
    @Positive
    @NotNull
    private int weight;
    @Positive
    @NotNull
    private BigDecimal price;
    private Long id;
}
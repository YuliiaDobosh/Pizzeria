package lpnu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lpnu.entity.enumeration.PizzaSize;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PizzaDTO {
    @NotNull
    private String name;
    @Positive
    @NotNull
    private BigDecimal price;
    @NotNull
    private PizzaSize size;
    @NotNull
    private int weight;
    @NotNull
    private Map<Long, Integer> ingredients;
    private Long id;
}
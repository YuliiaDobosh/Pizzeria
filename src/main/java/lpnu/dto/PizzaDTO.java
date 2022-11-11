package lpnu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lpnu.entity.enumeration.PizzaSize;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PizzaDTO {
    @NotNull
    private String name;
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
package lpnu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDTO {
    private Long pizzaId;
    @NotNull
    private String pizzaName;
    @NotNull
    private int amount;
    private BigDecimal pizzaPrice;
}

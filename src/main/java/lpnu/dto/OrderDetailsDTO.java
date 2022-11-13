package lpnu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDTO {
    private Long pizzaId;
    @NotNull
    private String pizzaName;
    @Positive
    @NotNull
    private int amount;
    private BigDecimal pizzaPrice;
}

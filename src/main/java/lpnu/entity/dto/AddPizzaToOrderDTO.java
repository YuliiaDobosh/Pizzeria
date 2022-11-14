package lpnu.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddPizzaToOrderDTO {
    @Positive
    @NotNull
    private Long orderId;
    @Positive
    @NotNull
    private Long pizzaId;
    private BigDecimal pizzaPrice;
    @Positive
    private int amount;
}

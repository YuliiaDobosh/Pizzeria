package lpnu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasketDetailsDTO {
    private Long pizzaId;
    private String pizzaName;
    private int amount;
}

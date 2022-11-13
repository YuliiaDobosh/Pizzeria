package lpnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
    @NotNull
    private Pizza pizza;
    @Positive
    @NotNull
    private int amount;
}

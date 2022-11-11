package lpnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    @NotNull
    private User user;
    @NotNull
    private List<OrderDetails> orders;
    @NotNull
    private LocalDateTime orderDateTime;
}

package lpnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PromoCode {
    private Long id;
    @NotNull
    private String name;
    @Min(value = 1, message = "Discount can't be less than 1")
    @Max(value = 100, message = "Discount can't be greater than 100")
    @NotNull
    private int discount;
    @NotNull
    private LocalDateTime expiringDate;
}

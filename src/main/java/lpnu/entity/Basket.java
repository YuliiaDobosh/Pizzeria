package lpnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Basket {
    private Long id;
    private Map<Pizza, Integer> orders;
    private int totalPrice;
}

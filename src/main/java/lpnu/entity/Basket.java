package lpnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Basket {
    private Long id;
    private LocalDateTime orderDateTime;
    private User user;
    private List<BasketDetails> orders;
}

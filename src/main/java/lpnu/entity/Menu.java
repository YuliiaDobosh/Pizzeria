package lpnu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    @NotNull
    private List<Pizza> allPizzas = new ArrayList<>();

    public Menu(final Menu menu) {
        this.allPizzas=menu.getAllPizzas();
    }
}

package lpnu.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lpnu.entity.enumeration.PizzaSize;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pizza {
    @NotNull
    private String name;
    @NotNull
    private BigDecimal price;
    @NotNull
    private PizzaSize size;
    @NotNull
    private int weight;
    @NotNull
    private Map<Long, Integer> ingredients;
    private Long id;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Pizza pizza)) return false;
        return getWeight() == pizza.getWeight() && Objects.equals(getName(), pizza.getName()) && Objects.equals(getPrice(), pizza.getPrice()) && getSize() == pizza.getSize() && Objects.equals(getIngredients(), pizza.getIngredients()); /*&& Objects.equals(getId(), pizza.getId());*/
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPrice(), getSize(), getWeight(), getIngredients(), getId());
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", size=" + size +
                ", weight=" + weight +
                ", ingredients=" + ingredients +
                ", id=" + id +
                '}';
    }
}
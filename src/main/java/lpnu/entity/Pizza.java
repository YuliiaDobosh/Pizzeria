package lpnu.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lpnu.entity.enumeration.PizzaSize;


import java.math.BigDecimal;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pizza {
    private String name;
    private BigDecimal price;
    private PizzaSize size;
    private Long id;
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Pizza pizza = (Pizza) o;
        return size == pizza.size && Objects.equals(name, pizza.name) && Objects.equals(price, pizza.price) && Objects.equals(id, pizza.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, size, id);
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", size=" + size +
                ", id=" + id +
                '}';
    }
}
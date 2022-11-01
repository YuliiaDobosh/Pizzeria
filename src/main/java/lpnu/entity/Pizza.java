package lpnu.entity;


import lombok.Data;


import java.math.BigDecimal;
import java.util.Objects;

@Data

public class Pizza {
    private String name;
    private BigDecimal price;
    private int size;
    private int available;
    private Long id;


    public Pizza() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(final int size) {
        this.size = size;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(final int available) {
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Pizza(final String name, final BigDecimal price, final int size, final int available, final Long id) {
        this.name = name;
        this.price = price;
        this.size = size;
        this.available = available;
        this.id = id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Pizza pizza = (Pizza) o;
        return size == pizza.size && available == pizza.available && Objects.equals(name, pizza.name) && Objects.equals(price, pizza.price) && Objects.equals(id, pizza.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, size, available, id);
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", size=" + size +
                ", available=" + available +
                ", id=" + id +
                '}';
    }
}
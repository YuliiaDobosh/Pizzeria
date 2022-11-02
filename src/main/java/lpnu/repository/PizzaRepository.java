package lpnu.repository;

import lpnu.entity.Pizza;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PizzaRepository {
    private List<Pizza> pizzas = new ArrayList<>();
    private Long id = 0L;

    public List<Pizza> getAllPizzas() {
        return new ArrayList<>(pizzas);
    }
    public Pizza save(final Pizza pizza){
        ++id;
        pizza.setId(id);

        pizzas.add(pizza);

        return pizza;
    }
    public Pizza findById(final Long id){
        return pizzas.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Pizza not found by id: " + id));
    }

    public void delete(final Long id){
        pizzas = pizzas.stream()
                .filter(e -> !e.getId().equals(id))
                .collect(Collectors.toList());
    }

    public Pizza update(final Pizza pizza) {
        final Pizza saved = findById(pizza.getId());

        saved.setName(pizza.getName());
        saved.setPrice(pizza.getPrice());
        saved.setSize(pizza.getSize());
        saved.setAvailable(pizza.getAvailable());

        return saved;
    }
}
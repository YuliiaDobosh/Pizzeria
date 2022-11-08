package lpnu.repository;

import lpnu.entity.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PizzaRepository {
    @Autowired
    IngredientRepository ingredientRepository;
    private List<Pizza> pizzas = new ArrayList<>();
    private Long id = 0L;

    public List<Pizza> getAllPizzas() {
        return new ArrayList<>(pizzas);
    }

    public Pizza save(final Pizza pizza) {
        ++id;
        pizza.setId(id);

        pizzas.add(pizza);

        return pizza;
    }

    public Pizza findById(final Long id) {
        return pizzas.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Pizza not found by id: " + id));
    }

    public void delete(final Long id) {
        pizzas = pizzas.stream()
                .filter(e -> !e.getId().equals(id))
                .collect(Collectors.toList());
    }

    public Pizza update(final Pizza pizza) {
        final Pizza saved = findById(pizza.getId());

        saved.setName(pizza.getName());
        saved.setPrice(pizza.getPrice());
        saved.setSize(pizza.getSize());
        saved.setIngredients(pizza.getIngredients());

        return saved;
    }

    public Pizza addIngredient(final Long pizzaId, final Long ingredientId, final Integer portions) {
        final Pizza pizzaToChange = findById(pizzaId);
        final Map<Long, Integer> newIngredients = new HashMap<>(pizzaToChange.getIngredients());
        int changedWeight = pizzaToChange.getWeight();

        if (newIngredients.containsKey(ingredientId)) {
            newIngredients.entrySet().stream()
                    .filter(e -> e.getKey().equals(ingredientId))
                    .collect(Collectors.toMap(Map.Entry::getKey, v -> v.setValue(v.getValue() + portions)));
        } else {
            newIngredients.put(ingredientId, portions);
        }
        changedWeight += newIngredients.entrySet()
                .stream()
                .mapToInt(e -> ingredientRepository.findById(e.getKey()).getWeight() * e.getValue()).sum();

        pizzaToChange.setIngredients(newIngredients);
        pizzaToChange.setWeight(changedWeight);

        return pizzaToChange;
    }
}
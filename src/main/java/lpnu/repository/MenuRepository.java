package lpnu.repository;

import lpnu.entity.Menu;
import lpnu.entity.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;

@Repository
public class MenuRepository {
    @Autowired
    PizzaRepository pizzaRepository;
    private Menu menu = new Menu();
    private Long id = 0L;

    public Menu getMenu() {
        return new Menu(menu);
    }

    public Menu save(final Pizza pizza) {
        ++id;
        pizza.setId(id);
        menu.getAllPizzas().add(pizza); //clone pizza

        return menu;
    }

    public Pizza findPizzaById(final Long pizzaId) {
        return menu.getAllPizzas()
                .stream()
                .filter(p -> p.getId().equals(pizzaId))
                .findFirst()
                .orElseThrow();
    }

    public Pizza update(final Pizza pizza) {
        final Pizza pizzaToUpdate = findPizzaById(pizza.getId());

        pizzaToUpdate.setName(pizza.getName());
        pizzaToUpdate.setPrice(pizza.getPrice());
        pizzaToUpdate.setSize(pizza.getSize());
        pizzaToUpdate.setIngredients(pizza.getIngredients());

        return pizzaToUpdate;
    }

    public void delete(final Long id) {
        menu.setAllPizzas(menu.getAllPizzas().stream()
                .filter(e -> !e.getId().equals(id))
                .collect(Collectors.toList()));
    }
}

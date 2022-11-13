package lpnu.repository;

import lpnu.entity.Menu;
import lpnu.entity.Pizza;
import lpnu.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;

@Repository
public class MenuRepository {
    @Autowired
    PizzaRepository pizzaRepository;
    private final Menu menu = new Menu();

    public Menu getMenu() {
        return new Menu(menu);
    }

    public Menu save(final Pizza pizza) {
        final Pizza pizzaToAdd = pizzaRepository.getAllPizzas()
                .stream()
                .filter(p->p.equals(pizza))
                .findFirst().orElseThrow();
        final Long id = pizzaToAdd.getId();
        pizza.setId(id);
        menu.getAllPizzas().add(pizza);

        return menu;
    }

    public Pizza findPizzaById(final Long pizzaId) {
        return menu.getAllPizzas()
                .stream()
                .filter(p -> p.getId().equals(pizzaId))
                .findFirst()
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST.value(),
                        "Pizza can not be found by id: " + pizzaId + "in menu."));
    }

    public Pizza update(final Pizza pizza) {
        final Pizza menuPizzaToUpdate = findPizzaById(pizza.getId());
        final Pizza pizzaToUpdate = pizzaRepository.findById(pizza.getId());

        menuPizzaToUpdate.setName(pizza.getName());
        menuPizzaToUpdate.setPrice(pizza.getPrice());
        menuPizzaToUpdate.setSize(pizza.getSize());
        menuPizzaToUpdate.setIngredients(pizza.getIngredients());

        pizzaToUpdate.setName(menuPizzaToUpdate.getName());
        pizzaToUpdate.setPrice(menuPizzaToUpdate.getPrice());
        pizzaToUpdate.setSize(menuPizzaToUpdate.getSize());
        pizzaToUpdate.setIngredients(menuPizzaToUpdate.getIngredients());

        return menuPizzaToUpdate;
    }

    public void delete(final Long pizzaId) {
        menu.setAllPizzas(menu.getAllPizzas().stream()
                .filter(e -> !e.getId().equals(pizzaId))
                .collect(Collectors.toList()));
    }
}

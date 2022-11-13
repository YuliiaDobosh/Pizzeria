package lpnu.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import lpnu.entity.Pizza;
import lpnu.exception.ServiceException;
import lpnu.util.JacksonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST.value(),
                        "Pizza can not be found by id: " + id));
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
    @PostConstruct
    public void init() {

        final Path file = Paths.get("pizza.txt");
        try {
            final String savedItemsAsString = Files.readString(file, StandardCharsets.UTF_16);
            pizzas = JacksonUtil.deserialize(savedItemsAsString, new TypeReference<>() {
            });

            if (pizzas == null) {
                pizzas = new ArrayList<>();
                return;
            }

            final long maxId = pizzas.stream().mapToLong(Pizza::getId).max().orElse(1);

            this.id = maxId + 1;

        } catch (final Exception e) {
            System.out.println("We have an issue in pizza");
            pizzas = new ArrayList<>();
        }
    }


    @PreDestroy
    public void preDestroy() {
        final Path file = Paths.get("pizza.txt");

        try {
            Files.writeString(file, JacksonUtil.serialize(pizzas), StandardCharsets.UTF_16);
        } catch (final Exception e) {
            System.out.println("We have an issue in pizza");
        }

    }
}
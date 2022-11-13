package lpnu.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import lpnu.util.JacksonUtil;

import lpnu.entity.Ingredient;
import lpnu.exception.ServiceException;
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
public class IngredientRepository {
    private List<Ingredient> ingredients = new ArrayList<>();
    private Long id = 0L;

    public List<Ingredient> getAllIngredients() {
        return new ArrayList<>(ingredients);
    }

    public Ingredient save(final Ingredient ingredient) {
        ++id;
        ingredient.setId(id);
        ingredients.add(ingredient);
        return ingredient;
    }

    public Ingredient findById(final Long id) {
        return ingredients.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ServiceException(HttpStatus.BAD_REQUEST.value(),
                        "Ingredient can not be found by id: " + id));
    }

    public void delete(final Long id) {
        ingredients = ingredients.stream()
                .filter(e -> !e.getId().equals(id))
                .collect(Collectors.toList());
    }

    public Ingredient update(final Ingredient ingredient) {
        final Ingredient saved = findById(ingredient.getId());

        saved.setName(saved.getName());
        saved.setWeight(saved.getWeight());
        saved.setPrice(saved.getPrice());
        saved.setId(saved.getId());

        return saved;
    }

    @PostConstruct
    public void init() {

        final Path file = Paths.get("ingredients.txt");
        try {
            final String savedItemsAsString = Files.readString(file, StandardCharsets.UTF_16);
            ingredients = JacksonUtil.deserialize(savedItemsAsString, new TypeReference<List<Ingredient>>() {
            });

            if (ingredients == null) {
                ingredients = new ArrayList<>();
                return;
            }

            final long maxId = ingredients.stream().mapToLong(Ingredient::getId).max().orElse(1);

            this.id = maxId + 1;

        } catch (final Exception e) {
            System.out.println("We have an issue in ingredients");
            ingredients = new ArrayList<>();
        }
    }


    @PreDestroy
    public void preDestroy() {
        final Path file = Paths.get("ingredients.txt");

        try {
            Files.writeString(file, JacksonUtil.serialize(ingredients), StandardCharsets.UTF_16);
        } catch (final Exception e) {
            System.out.println("We have an issue in ingredients");
        }

    }
}

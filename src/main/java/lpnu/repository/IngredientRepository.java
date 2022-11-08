package lpnu.repository;

import lpnu.entity.Ingredient;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Repository
public class IngredientRepository {
    private List<Ingredient> ingredients = new ArrayList<>();
    private Long id = 0L;

    public List<Ingredient> getAllIngredients(){
        return  new ArrayList<>(ingredients);
    }

    public Ingredient save(final Ingredient ingredient){
        ++id;
        ingredient.setId(id);
        ingredients.add(ingredient);
        return ingredient;
    }

    public Ingredient findById(final Long id){
        return ingredients.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Menu not found by id: " + id));
    }

    public void delete(final Long id) {
        ingredients = ingredients.stream()
                .filter(e -> !e.getId().equals(id))
                .collect(Collectors.toList());
    }

    public Ingredient update(final Ingredient ingredient){
        final Ingredient saved = findById(ingredient.getId());

        saved.setName(saved.getName());
        saved.setWeight(saved.getWeight());
        saved.setId(saved.getId());

        return saved;
    }
}

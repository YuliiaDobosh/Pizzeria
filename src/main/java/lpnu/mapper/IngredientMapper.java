package lpnu.mapper;

import lpnu.dto.IngredientDTO;
import lpnu.entity.Ingredient;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper {
    public static IngredientDTO toDTO(final Ingredient ingredient) {
        final IngredientDTO ingredientDTO = new IngredientDTO();

        ingredientDTO.setName(ingredient.getName());
        ingredientDTO.setWeight(ingredient.getWeight());
        ingredientDTO.setId(ingredient.getId());

        return ingredientDTO;
    }

    public static Ingredient toEntity(final IngredientDTO ingredientDTO){
        final Ingredient ingredient = new Ingredient();

        ingredient.setName(ingredientDTO.getName());
        ingredient.setWeight(ingredientDTO.getWeight());
        ingredient.setId(ingredientDTO.getId());

        return ingredient;
    }
}

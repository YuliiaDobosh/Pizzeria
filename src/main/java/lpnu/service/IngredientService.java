package lpnu.service;

import lpnu.dto.IngredientDTO;

import java.util.List;

public interface IngredientService {
    List<IngredientDTO> getAllIngredients();
    IngredientDTO create(IngredientDTO ingredientDTO);
    IngredientDTO findById(Long id);
    IngredientDTO update(IngredientDTO ingredientDTO);
    void delete(Long id);

}

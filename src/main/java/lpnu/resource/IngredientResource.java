package lpnu.resource;

import lpnu.entity.dto.IngredientDTO;


import lpnu.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ingredient")
public class IngredientResource {
    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public List<IngredientDTO> getAllIngredients() {
        return  ingredientService.getAllIngredients();
    }
    @GetMapping("/{id}")
    public IngredientDTO findById(final @PathVariable Long id) {
        return ingredientService.findById(id);
    }

    @PostMapping
    public IngredientDTO createIngredient(final @RequestBody @Validated IngredientDTO ingredientDTO) {
        return ingredientService.create(ingredientDTO);
    }
    @PutMapping
    public IngredientDTO updateIngredient(@RequestBody final IngredientDTO ingredientDTO) {
        return ingredientService.update(ingredientDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(final @PathVariable Long id) {
        ingredientService.delete(id);
        return ResponseEntity.ok().build();
    }
}

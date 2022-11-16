package lpnu.resource;


import lpnu.dto.PizzaDTO;
import lpnu.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pizza")
public class PizzaResource {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public List<PizzaDTO> getAllPizzas() {
        return pizzaService.getAllPizzas();
    }

    @GetMapping("/{id}")
    public PizzaDTO findById(@PathVariable final Long id) {
        return pizzaService.findById(id);
    }

    @PostMapping
    public PizzaDTO createPizza(@RequestBody @Validated final PizzaDTO pizzaDTO) {
        return pizzaService.create(pizzaDTO);
    }

    @PutMapping
    public PizzaDTO updatePizza(@RequestBody @Validated final PizzaDTO pizzaDTO) {
        return pizzaService.update(pizzaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable final Long id) {
        pizzaService.delete(id);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{pizzaId}/{ingredientId}/{portions}")
    public ResponseEntity removeIngredient(@PathVariable final Long pizzaId,
                                           @PathVariable final Long ingredientId,
                                           @PathVariable final Integer portions) {
        pizzaService.removeIngredient(pizzaId, ingredientId, portions);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/add-ingredient/{pizzaId}/{ingredientId}/{portions}")
    public PizzaDTO addIngredient(@PathVariable final Long pizzaId,
                                  @PathVariable final Long ingredientId,
                                  @PathVariable final Integer portions) {
        return pizzaService.addIngredient(pizzaId, ingredientId, portions);
    }
}
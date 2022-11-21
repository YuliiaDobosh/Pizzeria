package lpnu.service;

import lpnu.dto.PizzaDTO;
import lpnu.entity.Pizza;

import java.math.BigDecimal;
import java.util.List;

public interface PizzaService {
    List<PizzaDTO> getAllPizzas();

    PizzaDTO create(PizzaDTO pizzaDTO);

    PizzaDTO findById(Long id);

    PizzaDTO update(PizzaDTO pizzaDTO);

    void delete(Long id);

    PizzaDTO addIngredient(Long pizzaId, Long ingredientId, Integer portions);

    void removeIngredient(Long pizzaId, Long ingredientId, Integer portions);

    int minusIngredientWeight(Long pizzaId, Long ingredientId, Integer portions);

    BigDecimal minusIngredientPrice(Long pizzaId, Long ingredientId, Integer portions);

    int addIngredientWeight(Long pizzaId, Long ingredientId);

    BigDecimal addIngredientPrice(Long pizzaId, Long ingredientId);

    boolean validateAdditionsWeight(Pizza pizza);
    boolean validatePortionsToAdd(Pizza pizza, Long ingredientId, Integer portions);
}
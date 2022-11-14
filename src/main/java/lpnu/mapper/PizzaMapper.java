package lpnu.mapper;

import lpnu.entity.dto.PizzaDTO;
import lpnu.entity.Pizza;
import org.springframework.stereotype.Component;

@Component
public class PizzaMapper {
    public PizzaDTO toDTO(final Pizza pizza) {
        final PizzaDTO pizzaDTO = new PizzaDTO();

        pizzaDTO.setName(pizza.getName());
        pizzaDTO.setPrice(pizza.getPrice());
        pizzaDTO.setSize(pizza.getSize());
        pizzaDTO.setId(pizza.getId());
        pizzaDTO.setWeight(pizza.getWeight());
        if (pizza.getIngredients() != null) {
            pizzaDTO.setIngredients(pizza.getIngredients());
        }

        return pizzaDTO;
    }

    public Pizza toEntity(final PizzaDTO pizzaDTO) {
        final Pizza pizza = new Pizza();

        pizza.setName(pizzaDTO.getName());
        pizza.setPrice(pizzaDTO.getPrice());
        pizza.setSize(pizzaDTO.getSize());
        pizza.setId(pizzaDTO.getId());
        if (pizzaDTO.getIngredients() != null) {
            pizza.setIngredients(pizzaDTO.getIngredients());
        }
        pizza.setWeight(pizzaDTO.getSize().weight);
        return pizza;
    }
}
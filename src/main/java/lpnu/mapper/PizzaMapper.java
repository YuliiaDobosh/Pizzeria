package lpnu.mapper;

import lpnu.dto.PizzaDTO;
import lpnu.entity.Pizza;

public class PizzaMapper {
    public static PizzaDTO toDTO(final Pizza pizza){
        final PizzaDTO pizzaDTO = new PizzaDTO();


        pizzaDTO.setName(pizza.getName());
        pizzaDTO.setPrice(pizza.getPrice());
        pizzaDTO.setSize(pizza.getSize());
        pizzaDTO.setId(pizza.getId());


        return pizzaDTO;
    }

    public static Pizza toEntity(final PizzaDTO pizzaDTO){
        final Pizza pizza = new Pizza();

        pizza.setName(pizzaDTO.getName());
        pizza.setPrice(pizzaDTO.getPrice());
        pizza.setSize(pizzaDTO.getSize());
        pizza.setId(pizzaDTO.getId());
        return pizza;
    }
}
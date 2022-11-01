package lpnu.mapper;

import lpnu.dto.PizzaDTO;
import lpnu.entity.Pizza;

public class PizzaMapper {
    public static PizzaDTO toDTO(final Pizza pizza){
        final PizzaDTO pizzaDTO = new PizzaDTO();


        pizzaDTO.setName(pizza.getName());
        pizzaDTO.setPrice(pizza.getPrice());
        pizzaDTO.setAvailable(pizza.getAvailable());
        pizzaDTO.setSize(pizza.getSize());
        pizzaDTO.setId(pizza.getId());


        return pizzaDTO;
    }

    public static Pizza toEntity(final PizzaDTO pizzaDTO){
        final Pizza pizza = new Pizza();

        pizza.setName(pizza.getName());
        pizza.setPrice(pizza.getPrice());
        pizza.setAvailable(pizza.getAvailable());
        pizza.setSize(pizza.getSize());
        pizza.setId(pizza.getId());
        return pizza;
    }
}
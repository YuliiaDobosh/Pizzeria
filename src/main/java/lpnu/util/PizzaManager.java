package lpnu.util;

import lpnu.dto.PizzaDTO;
import lpnu.entity.Pizza;
import lpnu.repository.PizzaRepository;
import lpnu.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
public class PizzaManager {
    @Autowired
    PizzaService pizzaService;
    public PizzaDTO getHawaiiPizza(){
        return pizzaService.create(pizzaService.findByName("Hawaii"));
    }
    public PizzaDTO getMargheritaPizza(){
        return pizzaService.create(pizzaService.findByName("Margherita"));
    }
    public PizzaDTO getCapricciosaPizza(){
        return pizzaService.create(pizzaService.findByName("Capricciosa"));
    }
}

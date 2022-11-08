package lpnu.util;

import lpnu.dto.PizzaDTO;
import lpnu.entity.Pizza;
import lpnu.repository.PizzaRepository;
import lpnu.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;

public class PizzaManager {
    @Autowired
    PizzaService pizzaService;

    public PizzaDTO getStandardPizza(final Long id) {
        return pizzaService.create(pizzaService.findById(id));
    }
}

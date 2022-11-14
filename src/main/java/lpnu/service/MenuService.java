package lpnu.service;

import lpnu.entity.dto.MenuDTO;
import lpnu.entity.dto.PizzaDTO;

public interface MenuService {
    MenuDTO getMenu();
    MenuDTO create(PizzaDTO pizzaDTO);

    PizzaDTO findPizzaById(Long pizzaId);

    PizzaDTO update(PizzaDTO pizzaDTO);

    void delete(Long id);
}
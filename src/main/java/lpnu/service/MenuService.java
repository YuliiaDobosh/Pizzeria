package lpnu.service;

import lpnu.dto.MenuDTO;
import lpnu.dto.PizzaDTO;

import java.util.List;

public interface MenuService {
    List<MenuDTO> getAllMenus();

    MenuDTO create(MenuDTO menuDTO);

    PizzaDTO findPizzaById(Long menuId, Long pizzaId);

    MenuDTO findById(Long id);

    MenuDTO update(MenuDTO menuDTO);

    void delete(Long id);

    //todo
    // MenuDTO addPizza(final PizzaDTO pizzaDTO, final Long id);
    // remove Pizza
    //update Pizza
}
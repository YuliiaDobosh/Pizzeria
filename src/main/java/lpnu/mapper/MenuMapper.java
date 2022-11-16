package lpnu.mapper;

import lpnu.dto.MenuDTO;
import lpnu.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MenuMapper {
    @Autowired
    PizzaMapper pizzaMapper;
    public Menu toEntity(final MenuDTO menuDTO) {
        final Menu menu = new Menu();
        if (menuDTO.getAllPizzas() != null) {
            menu.setAllPizzas(menuDTO.getAllPizzas()
                    .stream()
                    .map(pizzaMapper::toEntity)
                    .toList());
        }
        return menu;
    }

    public MenuDTO toDTO(final Menu menu) {
        final MenuDTO menuDTO = new MenuDTO();
        if (menu.getAllPizzas() != null) {
            menuDTO.setAllPizzas(menu.getAllPizzas()
                    .stream()
                    .map(pizzaMapper::toDTO)
                    .toList());
        }
        return menuDTO;
    }
}

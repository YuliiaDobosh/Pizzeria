package lpnu.mapper;

import lpnu.dto.MenuDTO;
import lpnu.entity.Menu;
import org.springframework.stereotype.Component;


@Component
public class MenuMapper {
    public Menu toEntity(final MenuDTO menuDTO) {
        final Menu menu = new Menu();
        menu.setId(menuDTO.getId());
        if (menuDTO.getAllPizzas() != null) {
            menu.setAllPizzas(menuDTO.getAllPizzas());
        }
        return menu;
    }

    public MenuDTO toDTO(final Menu menu) {
        final MenuDTO menuDTO = new MenuDTO();
        menuDTO.setId(menu.getId());
        if (menu.getAllPizzas() != null) {
            menuDTO.setAllPizzas(menu.getAllPizzas());
        }
        return menuDTO;
    }
}

package lpnu.mapper;

import lpnu.dto.MenuDTO;
import lpnu.entity.Menu;
import org.springframework.stereotype.Component;

@Component
public class MenuMapper {
public Menu toEntity(final MenuDTO menuDTO){
    return new Menu(menuDTO.getId(), menuDTO.getAllPizzas());
}
public MenuDTO toDTO (final Menu menu){
    return new MenuDTO(menu.getId(), menu.getAllPizzas());
}
}

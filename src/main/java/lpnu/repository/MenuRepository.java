package lpnu.repository;

import lpnu.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MenuRepository {
    private List<Menu> menus = new ArrayList<>();
    private Long id = 0L;

    public List<Menu> getAllMenus(){
        return new ArrayList<>(menus);
    }

    public Menu save(final Menu menu){
        ++id;
        menu.setId(id);

        menus.add(menu);

        return menu;
    }
    public Menu findById(final Long id){
        return menus.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Menu not found by id: " + id));
    }
    public void delete(final Long id) {
        menus = menus.stream()
                .filter(e -> !e.getId().equals(id))
                .collect(Collectors.toList());
    }

    public Menu update(final Menu menu){
        final Menu saved = findById(menu.getId());

        saved.setAllPizzas(menu.getAllPizzas());

        return saved;
    }
}

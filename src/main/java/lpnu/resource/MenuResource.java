package lpnu.resource;

import lpnu.dto.MenuDTO;
import lpnu.dto.PizzaDTO;
import lpnu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menu")
public class MenuResource {

    @Autowired
    private MenuService menuService;

    @GetMapping()
    public List<MenuDTO> getAllMenus() {
        return menuService.getAllMenus();
    }
    @GetMapping("/{menuId}/{pizzaId}")
    public PizzaDTO findPizzaById(@PathVariable final Long menuId, @PathVariable final Long pizzaId){
        return menuService.findPizzaById(menuId, pizzaId);
    }
    @GetMapping("/{id}")
    public MenuDTO findById(@PathVariable final Long id) {
        return menuService.findById(id);
    }

    @PostMapping
    public MenuDTO createMenu(@RequestBody @Validated final MenuDTO menuDTO) {
        return menuService.create(menuDTO);
    }

    @PutMapping
    public MenuDTO updateMenu(@RequestBody final MenuDTO menuDTO) {
        return menuService.update(menuDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable final Long id) {
        menuService.delete(id);
        return ResponseEntity.ok().build();
    }
}
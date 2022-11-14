package lpnu.resource;

import lpnu.entity.dto.MenuDTO;
import lpnu.entity.dto.PizzaDTO;
import lpnu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/menu")
public class MenuResource {

    @Autowired
    private MenuService menuService;
    @GetMapping
    public MenuDTO getMenu(){
        return menuService.getMenu();
    }
    @GetMapping("/{pizzaId}")
    public PizzaDTO findPizzaById(@PathVariable final Long pizzaId){
        return menuService.findPizzaById(pizzaId);
    }

    @PostMapping
    public MenuDTO createMenu(@RequestBody @Validated final PizzaDTO pizzaDTO) {
        return menuService.create(pizzaDTO);
    }

    @PutMapping
    public PizzaDTO updateMenu(@RequestBody final PizzaDTO pizzaDTO) {
        return menuService.update(pizzaDTO);
    }

    @DeleteMapping("/{pizzaId}")
    public ResponseEntity delete(@PathVariable final Long pizzaId) {
        menuService.delete(pizzaId);
        return ResponseEntity.ok().build();
    }
}
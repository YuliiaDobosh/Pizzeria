package lpnu.resource;

import lpnu.dto.BasketDTO;
import lpnu.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/basket")
public class BasketResource {
    @Autowired
    private BasketService basketService;

    @GetMapping
    public List<BasketDTO> getAllBaskets() {
        return basketService.getAllBaskets();
    }

    @GetMapping("/{id}")
    public BasketDTO findById(final @PathVariable Long id) {
        return basketService.findById(id);
    }

    @PostMapping
    public BasketDTO createBasket(final @RequestBody @Validated BasketDTO basketDTO) {
        return basketService.create(basketDTO);
    }

    /*@PostMapping("/add-item")
    public void addItemToBasket(final @RequestBody @Validated AddItemToBasketDTO addDTO) {
        basketService.addItemToBasket(addDTO);
    }*/
    @DeleteMapping("/{id}")
    public ResponseEntity delete(final @PathVariable Long id) {
        basketService.delete(id);
        return ResponseEntity.ok().build();
    }
}

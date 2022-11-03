package lpnu.mapper;

import lpnu.dto.BasketDTO;
import lpnu.entity.Basket;
import lpnu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BasketMapper {
    @Autowired
    private BasketDetailsMapper basketDetailsMapper;
    @Autowired
    private UserRepository userRepository;

    public Basket toEntity(final BasketDTO basketDTO){
        final Basket basket = new Basket();

        basket.setId(basketDTO.getId());
        basket.setUser(userRepository.findById(basketDTO.getUserId()));
        if (basketDTO.getOrders() != null) {
            basket.setOrders(basketDTO.getOrders().stream().map(basketDetailsMapper::toEntity).collect(Collectors.toList()));
        }
        basket.setOrderDateTime(basketDTO.getOrderDateTime());

        return basket;
    }
    public BasketDTO toDTO(final Basket basket) {
        final BasketDTO basketDTO = new BasketDTO();

        basketDTO.setId(basket.getId());
        basketDTO.setUserId(basket.getUser().getId());
        basketDTO.setOrderDateTime(basket.getOrderDateTime());
        if (basket.getOrders() != null) {
            basketDTO.setOrders(basket.getOrders().stream().map(basketDetailsMapper::toDTO).collect(Collectors.toList()));
        }

        return basketDTO;
    }
}

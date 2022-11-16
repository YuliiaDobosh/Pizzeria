package lpnu.service;

import lpnu.dto.AddPizzaToOrderDTO;
import lpnu.dto.OrderDTO;
import lpnu.entity.PromoCode;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();

    OrderDTO create(OrderDTO orderDTO);
    OrderDTO update(OrderDTO orderDTO);
    OrderDTO findById(Long id);

    void addPizzaToOrder(AddPizzaToOrderDTO addDTO);

    void removePizza(Long orderId, Long pizzaId);

    boolean validateIsPizzaInMenu(Long pizzaId);

    BigDecimal pay(final Long orderId, final PromoCode promoCode);
    boolean validatePromoCode(final Long orderId, final PromoCode promoCode);

    void delete(Long id);
}


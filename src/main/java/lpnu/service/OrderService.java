package lpnu.service;

import lpnu.entity.dto.AddPizzaToOrderDTO;
import lpnu.entity.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();

    OrderDTO create(OrderDTO orderDTO);

    OrderDTO findById(Long id);

    void addPizzaToOrder(AddPizzaToOrderDTO addDTO);

    void removePizza(Long orderId, Long pizzaId);

    boolean validateIsPizzaInMenu(Long pizzaId);

    void delete(Long id);
}


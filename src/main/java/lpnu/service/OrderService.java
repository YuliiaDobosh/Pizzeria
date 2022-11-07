package lpnu.service;

import lpnu.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
    OrderDTO create(OrderDTO orderDTO);
    OrderDTO findById(Long id);

    void delete(Long id);
}

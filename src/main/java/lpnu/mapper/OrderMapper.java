package lpnu.mapper;

import lpnu.dto.OrderDTO;
import lpnu.entity.Order;
import lpnu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {
    @Autowired
    private OrderDetailsMapper orderDetailsMapper;
    @Autowired
    private UserRepository userRepository;

    public Order toEntity(final OrderDTO orderDTO){
        final Order order = new Order();

        order.setId(orderDTO.getId());
        order.setUser(userRepository.findById(orderDTO.getUserId()));
        if (orderDTO.getOrders() != null) {
            order.setOrders(orderDTO.getOrders().stream().map(orderDetailsMapper::toEntity).collect(Collectors.toList()));
        }
        order.setOrderDateTime(orderDTO.getOrderDateTime());

        return order;
    }
    public OrderDTO toDTO(final Order order) {
        final OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(order.getId());
        orderDTO.setUserId(order.getUser().getId());
        orderDTO.setOrderDateTime(order.getOrderDateTime());
        if (order.getOrders() != null) {
            orderDTO.setOrders(order.getOrders().stream().map(orderDetailsMapper::toDTO).collect(Collectors.toList()));
        }

        return orderDTO;
    }
}

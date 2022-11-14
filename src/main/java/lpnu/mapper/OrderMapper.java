package lpnu.mapper;

import lpnu.entity.dto.OrderDTO;
import lpnu.entity.Order;
import lpnu.repository.UserRepository;
import lpnu.service.impl.TotalPriceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {
    @Autowired
    private OrderDetailsMapper orderDetailsMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TotalPriceServiceImpl totalPriceService;

    public Order toEntity(final OrderDTO orderDTO) {
        final Order order = new Order();

        order.setId(orderDTO.getId());
        order.setUser(userRepository.findById(orderDTO.getUserId()));
        if (orderDTO.getOrders() != null) {
            order.setOrders(orderDTO.getOrders().stream().map(orderDetailsMapper::toEntity).collect(Collectors.toList()));
        }
        order.setOrderDateTime(orderDTO.getOrderDateTime());
        order.setTotalPrice(orderDTO.getTotalPrice());
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
        orderDTO.setTotalPrice(totalPriceService.getTotalPrice(orderDTO.getId()));
        return orderDTO;
    }
}

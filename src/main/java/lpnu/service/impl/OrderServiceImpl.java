package lpnu.service.impl;

import lpnu.dto.OrderDTO;
import lpnu.entity.Order;
import lpnu.entity.OrderDetails;
import lpnu.mapper.OrderDetailsMapper;
import lpnu.mapper.OrderMapper;
import lpnu.repository.OrderRepository;
import lpnu.repository.PizzaRepository;
import lpnu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailsMapper orderDetailsMapper;


    @Autowired
    private PizzaRepository pizzaRepository;


    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.getAllOrders().stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO create(final OrderDTO orderDTO) {
        final Order order = orderMapper.toEntity(orderDTO);
        final List<OrderDetails> orderDetails = orderDTO.getOrders()
                .stream()
                .map(orderDetailsMapper::toEntity)
                .collect(Collectors.toList());

        order.setOrderDateTime(LocalDateTime.now());
        order.setOrders(orderDetails);

        orderRepository.save(order);

        return orderMapper.toDTO(order);
    }

    @Override
    public OrderDTO findById(final Long id) {
        return orderMapper.toDTO(orderRepository.findById(id));
    }

    @Override
    public void delete(final Long id) {
        orderRepository.delete(id);
    }
}

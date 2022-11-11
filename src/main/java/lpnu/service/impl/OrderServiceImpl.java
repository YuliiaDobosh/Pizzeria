package lpnu.service.impl;

import lpnu.dto.AddPizzaToOrderDTO;
import lpnu.dto.OrderDTO;
import lpnu.entity.Order;
import lpnu.entity.OrderDetails;
import lpnu.entity.Pizza;
import lpnu.mapper.OrderDetailsMapper;
import lpnu.mapper.OrderMapper;
import lpnu.repository.OrderRepository;
import lpnu.repository.PizzaRepository;
import lpnu.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

        order.setOrderDateTime(LocalDateTime.now());
        order.setOrders(new ArrayList<>());

        orderRepository.save(order);

        return orderMapper.toDTO(order);
    }

    @Override
    public OrderDTO findById(final Long id) {
        return orderMapper.toDTO(orderRepository.findById(id));
    }
    @Override
    public void addPizzaToOrder(final AddPizzaToOrderDTO addDTO) {

        final Order order = orderRepository.findById(addDTO.getOrderId());

        final Pizza pizza = pizzaRepository.findById(addDTO.getPizzaId());



       final boolean isPizzaInOrder = order.getOrders().stream()
                .map(OrderDetails::getPizza)
                .anyMatch(e -> e.equals(pizza));

        if(isPizzaInOrder){
            final OrderDetails savedOrderDetails = order.getOrders().stream()
                    .filter(e -> e.getPizza().equals(pizza))
                    .findFirst().get();

            savedOrderDetails.setAmount(savedOrderDetails.getAmount() + addDTO.getAmount());

            orderRepository.update(order);

        } else {
            final OrderDetails orderDetails = new OrderDetails(pizza, addDTO.getAmount());
            order.getOrders().add(orderDetails);

            orderRepository.update(order);
        }
    }

    @Override
    public void delete(final Long id) {
        orderRepository.delete(id);
    }
}

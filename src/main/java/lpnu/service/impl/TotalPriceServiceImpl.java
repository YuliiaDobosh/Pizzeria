package lpnu.service.impl;

import lpnu.entity.Order;
import lpnu.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TotalPriceServiceImpl {
    @Autowired
    OrderRepository orderRepository;

    public BigDecimal getTotalPrice(final Long id) {
        final Order order = orderRepository.findById(id);
        return order.getOrders()
                .stream()
                .map(orderDetails -> orderDetails.getPizza().getPrice().multiply(new BigDecimal(orderDetails.getAmount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

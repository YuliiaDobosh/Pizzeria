package lpnu.mapper;

import lpnu.entity.dto.OrderDetailsDTO;
import lpnu.entity.OrderDetails;
import lpnu.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderDetailsMapper {
    @Autowired
    private PizzaRepository pizzaRepository;

    public OrderDetails toEntity(final OrderDetailsDTO orderDetailsDTO){
        final OrderDetails orderDetails = new OrderDetails();

        orderDetails.setPizza(pizzaRepository.findById(orderDetailsDTO.getPizzaId()));
        orderDetails.setAmount(orderDetailsDTO.getAmount());

        return orderDetails;
    }
    public OrderDetailsDTO toDTO(final OrderDetails orderDetails){
        final OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();

        orderDetailsDTO.setPizzaId(orderDetails.getPizza().getId());
        orderDetailsDTO.setPizzaName(orderDetails.getPizza().getName());
        orderDetailsDTO.setPizzaPrice(orderDetails.getPizza().getPrice());
        orderDetailsDTO.setAmount(orderDetails.getAmount());

        return orderDetailsDTO;
    }
}


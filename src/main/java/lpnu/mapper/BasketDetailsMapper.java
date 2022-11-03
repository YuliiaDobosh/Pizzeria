package lpnu.mapper;

import lpnu.dto.BasketDetailsDTO;
import lpnu.entity.BasketDetails;
import lpnu.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BasketDetailsMapper {
    @Autowired
    private PizzaRepository pizzaRepository;

    public BasketDetails toEntity(final BasketDetailsDTO basketDetailsDTO){
        final BasketDetails basketDetails = new BasketDetails();

        basketDetails.setPizza(pizzaRepository.findById(basketDetailsDTO.getPizzaId()));
        basketDetails.setAmount(basketDetailsDTO.getAmount());

        return basketDetails;
    }
    public BasketDetailsDTO toDTO(final BasketDetails basketDetails){
        final BasketDetailsDTO basketDetailsDTO = new BasketDetailsDTO();

        basketDetailsDTO.setPizzaId(basketDetails.getPizza().getId());
        basketDetailsDTO.setPizzaName(basketDetails.getPizza().getName());
        basketDetailsDTO.setAmount(basketDetails.getAmount());

        return basketDetailsDTO;
    }
}


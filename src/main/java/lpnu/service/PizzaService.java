package lpnu.service;

import lpnu.dto.PizzaDTO;

import java.util.List;

public interface PizzaService {
    List<PizzaDTO> getAllPizzas();

    PizzaDTO create(PizzaDTO pizzaDTO);

    PizzaDTO findById(Long id);

    PizzaDTO update(PizzaDTO pizzaDTO);

    void delete(Long id);
}
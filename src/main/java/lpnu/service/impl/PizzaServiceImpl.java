package lpnu.service.impl;

import lpnu.dto.PizzaDTO;
import lpnu.entity.Pizza;
import lpnu.mapper.PizzaMapper;
import lpnu.repository.PizzaRepository;
import lpnu.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaServiceImpl implements PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private PizzaMapper pizzaMapper;
    @Override
    public List<PizzaDTO> getAllPizzas() {
        return pizzaRepository.getAllPizzas().stream()
                .map(pizzaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PizzaDTO create(final PizzaDTO pizzaDTO) {
        final Pizza pizza = pizzaMapper.toEntity(pizzaDTO);
        pizzaRepository.save(pizza);
        return pizzaMapper.toDTO(pizza);
    }

    @Override
    public void delete(final Long id) {
        pizzaRepository.delete(id);
    }


    @Override
    public PizzaDTO update(final PizzaDTO pizzaDTO) {
        final Pizza pizza = pizzaMapper.toEntity(pizzaDTO);

        pizzaRepository.update(pizza);

        return pizzaMapper.toDTO(pizza);
    }

    @Override
    public PizzaDTO findById(final Long id) {
        return pizzaMapper.toDTO(pizzaRepository.findById(id));
    }
    public PizzaDTO addIngredient( final Long pizzaId,  final Long ingredientId, final Integer portions){
        return pizzaMapper.toDTO(pizzaRepository.addIngredient(pizzaId,ingredientId,portions));
    }
}
package lpnu.service.impl;

import lpnu.dto.PizzaDTO;
import lpnu.entity.Pizza;
import lpnu.mapper.PizzaMapper;
import lpnu.repository.IngredientRepository;
import lpnu.repository.PizzaRepository;
import lpnu.service.PizzaService;
import lpnu.util.ValidatePortions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PizzaServiceImpl implements PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private IngredientRepository ingredientRepository;
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

    public PizzaDTO addIngredient(final Long pizzaId, final Long ingredientId, final Integer portions) {
        final Pizza pizzaToChange = pizzaRepository.findById(pizzaId);
        final Map<Long, Integer> newIngredients = new HashMap<>(pizzaToChange.getIngredients());

        if (newIngredients.containsKey(ingredientId)) {
            newIngredients.entrySet().stream()
                    .filter(e -> e.getKey().equals(ingredientId))
                    .collect(Collectors.toMap(Map.Entry::getKey, v -> v.setValue(v.getValue() + portions)));
        } else {
            newIngredients.put(ingredientId, portions);
        }

        pizzaToChange.setIngredients(newIngredients);
        pizzaToChange.setWeight(addIngredientWeight(pizzaId, ingredientId));
        pizzaToChange.setPrice(addIngredientPrice(pizzaId, ingredientId));
        if (ValidatePortions.validateIngredientPortions(pizzaToChange, portions)) {
            return pizzaMapper.toDTO(pizzaToChange);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int addIngredientWeight(final Long pizzaId, final Long ingredientId) {
        return pizzaRepository.findById(pizzaId).getWeight() + ingredientRepository.findById(ingredientId).getWeight();
    }

    public BigDecimal addIngredientPrice(final Long pizzaId, final Long ingredientId) {
        return pizzaRepository.findById(pizzaId).getPrice().add(ingredientRepository.findById(ingredientId).getPrice());
    }
}
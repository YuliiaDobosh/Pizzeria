package lpnu.service.impl;

import lpnu.dto.PizzaDTO;
import lpnu.entity.Pizza;
import lpnu.exception.ServiceException;
import lpnu.mapper.PizzaMapper;
import lpnu.repository.IngredientRepository;
import lpnu.repository.PizzaRepository;
import lpnu.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    private static final int additionsWeightLimit = 200;
    private static final Integer portionLimitToAdd = 1;
    private static final Integer portionLimitInPizza = 2;


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

    @Override
    public PizzaDTO addIngredient(final Long pizzaId, final Long ingredientId, final Integer portions) {
        final Pizza pizzaToChange = pizzaRepository.findById(pizzaId);
        final Map<Long, Integer> newIngredients = new HashMap<>(pizzaToChange.getIngredients());
        if (!validateAdditionsWeight(pizzaToChange)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST.value(),
                    "Weight of all additions can not be greater than 200g");
        }
        if (!validatePortionsToAdd(pizzaToChange, ingredientId, portions)) {
            throw new ServiceException(HttpStatus.BAD_REQUEST.value(),
                    "You can not add ingredient to pizza. Portions can not be greater than 1.");

        }
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
        return pizzaMapper.toDTO(pizzaToChange);
    }

    public void removeIngredient(final Long pizzaId, final Long ingredientId, final Integer portions) {
        final Pizza pizza = pizzaRepository.findById(pizzaId);
        final Map<Long, Integer> newIngredients = new HashMap<>(pizza.getIngredients());
        newIngredients.entrySet()
                .stream()
                .filter(e -> e.getKey().equals(ingredientId))
                .collect(Collectors.toMap(Map.Entry::getKey, v -> v.setValue(v.getValue() - portions)));
        pizza.setIngredients(newIngredients);
        pizza.setWeight(minusIngredientWeight(pizzaId, ingredientId, portions));
        pizza.setPrice(minusIngredientPrice(pizzaId, ingredientId, portions));
    }

    @Override
    public int addIngredientWeight(final Long pizzaId, final Long ingredientId) {
        return pizzaRepository.findById(pizzaId).getWeight() + ingredientRepository.findById(ingredientId).getWeight();
    }

    public int minusIngredientWeight(final Long pizzaId, final Long ingredientId, final Integer portions) {
        return pizzaRepository.findById(pizzaId).getWeight() - ingredientRepository.findById(ingredientId).getWeight() * portions;
    }

    public BigDecimal minusIngredientPrice(final Long pizzaId, final Long ingredientId, final Integer portions) {
        final BigDecimal tmp = ingredientRepository.findById(ingredientId).getPrice().multiply(new BigDecimal(portions));
        return pizzaRepository.findById(pizzaId).getPrice().subtract(tmp);
    }

    @Override
    public BigDecimal addIngredientPrice(final Long pizzaId, final Long ingredientId) {
        return pizzaRepository.findById(pizzaId).getPrice().add(ingredientRepository.findById(ingredientId).getPrice());
    }

    public boolean validateAdditionsWeight(final Pizza pizza) {
        final int totalWeight = pizza.getWeight();
        return totalWeight - pizza.getSize().weight < additionsWeightLimit;
    }

    @Override
    public boolean validatePortionsToAdd(final Pizza pizza, final Long ingredientId, final Integer portions) {
        final Map<Long, Integer> wrongPortions = pizza.getIngredients().entrySet().stream()
                .filter(e -> e.getKey().equals(ingredientId))
                .filter(e -> e.getValue() >= portionLimitInPizza)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return wrongPortions.size() == 0 && portions.equals(portionLimitToAdd);

    }
}
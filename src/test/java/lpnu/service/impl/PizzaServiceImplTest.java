package lpnu.service.impl;

import lpnu.Application;
import lpnu.dto.PizzaDTO;
import lpnu.entity.Ingredient;
import lpnu.entity.Pizza;
import lpnu.entity.enumeration.PizzaSize;
import lpnu.exception.ServiceException;
import lpnu.service.PizzaService;
import lpnu.mapper.PizzaMapper;
import lpnu.repository.PizzaRepository;
import lpnu.repository.IngredientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class PizzaServiceImplTest {
    @Test
    public void test_findPizzaById_pizzaExist() throws Exception {
        final PizzaRepository pizzaRepository = Mockito.mock(PizzaRepository.class);
        final PizzaMapper pizzaMapper = Mockito.mock(PizzaMapper.class);
        final IngredientRepository ingredientRepository = Mockito.mock(IngredientRepository.class);

        final PizzaService pizzaService = new PizzaServiceImpl(pizzaRepository, ingredientRepository, pizzaMapper);

        final String pizzaName = "Hawaii";
        final BigDecimal pizzaPrice = new BigDecimal(300);
        final int pizzaWeight = 150;
        final Map<Long, Integer> pizzaIngredients = new HashMap<>();
        pizzaIngredients.put(1L, 15);

        final Pizza pizza = new Pizza(pizzaName, pizzaPrice, PizzaSize.MEDIUM, pizzaWeight, pizzaIngredients, 1L);

        when(pizzaRepository.findById(1L)).thenReturn(pizza);
        when(pizzaMapper.toDTO(any())).thenCallRealMethod();
        when(ingredientRepository.findById(1L)).thenCallRealMethod();

        final PizzaDTO pizzaDTO = pizzaService.findById(1L);

        assertNotNull(pizzaDTO);

        assertEquals(pizzaName, pizzaDTO.getName());
        assertEquals(pizzaPrice, pizzaDTO.getPrice());
        assertEquals(pizza.getSize(), pizzaDTO.getSize());
        assertEquals(pizzaWeight, pizzaDTO.getWeight());
        assertEquals(pizzaIngredients, pizzaDTO.getIngredients());
        assertEquals(1L, pizzaDTO.getId().longValue());
    }

    @Test
    public void test_findPizzaById_pizzaNotExist() throws Exception {
        final PizzaRepository pizzaRepository = Mockito.mock(PizzaRepository.class);
        final PizzaMapper pizzaMapper = Mockito.mock(PizzaMapper.class);
        final IngredientRepository ingredientRepository = Mockito.mock(IngredientRepository.class);

        final PizzaService pizzaService = new PizzaServiceImpl(pizzaRepository, ingredientRepository, pizzaMapper);

        when(pizzaRepository.findById(1L)).thenThrow(new ServiceException(400, "error some exception"));
        when(pizzaMapper.toDTO(any())).thenCallRealMethod();
        when(ingredientRepository.findById(1L)).thenCallRealMethod();

        try {
            final PizzaDTO pizzaDTO = pizzaService.findById(1L);
            fail();
        } catch (final ServiceException e) {

        } catch (final Exception e) {
            fail();
        }
    }

    @Test
    public void test_addIngredient_notValidWeightOfAdditions() throws Exception {
        final PizzaRepository pizzaRepository = Mockito.mock(PizzaRepository.class);
        final PizzaMapper pizzaMapper = Mockito.mock(PizzaMapper.class);
        final IngredientRepository ingredientRepository = Mockito.mock(IngredientRepository.class);

        final PizzaService pizzaService = new PizzaServiceImpl(pizzaRepository, ingredientRepository, pizzaMapper);

        final String pizzaName = "Hawaii";
        final BigDecimal pizzaPrice = new BigDecimal(300);
        final int pizzaWeight = PizzaSize.MEDIUM.weight;
        final Map<Long, Integer> pizzaIngredients = new HashMap<>();
        pizzaIngredients.put(1L, 15);

        final Pizza pizza = new Pizza(pizzaName, pizzaPrice, PizzaSize.MEDIUM,
                pizzaWeight, pizzaIngredients, 1L);

        when(pizzaRepository.findById(1L)).thenReturn(pizza);

        try {
            pizzaService.addIngredient(1L, 1L, 1);
            fail();
        } catch (final ServiceException e) {

        } catch (final Exception e) {
            fail();
        }
    }

    @Test
    public void test_validatePortionsToAdd_inValidNumberOfPortions() throws Exception {
        final PizzaRepository pizzaRepository = Mockito.mock(PizzaRepository.class);
        final PizzaMapper pizzaMapper = Mockito.mock(PizzaMapper.class);
        final IngredientRepository ingredientRepository = Mockito.mock(IngredientRepository.class);

        final PizzaService pizzaService = new PizzaServiceImpl(pizzaRepository, ingredientRepository, pizzaMapper);

        final String pizzaName = "Hawaii";
        final BigDecimal pizzaPrice = new BigDecimal(300);
        final int pizzaWeight = PizzaSize.MEDIUM.weight;
        final Map<Long, Integer> pizzaIngredients = new HashMap<>();
        pizzaIngredients.put(1L, 15);

        final Pizza pizza = new Pizza(pizzaName, pizzaPrice, PizzaSize.MEDIUM,
                pizzaWeight, pizzaIngredients, 1L);

        final Ingredient ingredient = new Ingredient();
        ingredient.setId(3L);
        ingredient.setWeight(30);
        ingredient.setName("tomato");
        ingredient.setPrice(new BigDecimal(50));
//        final Ingredient ingredient = new Ingredient();
//        ingredient.setId(3L);
//        ingredient.setWeight(30);
//        ingredient.setName("tomato");
//        ingredient.setPrice(new BigDecimal(50));

//        final Map<Long, Integer> portion = new HashMap<>();
//        portion.put(1L, 1);
//
//        when(pizzaRepository.findById(1L)).thenReturn(pizza);
//        when(ingredientRepository.findById(3L)).thenReturn(ingredient);
//
//        when(pizzaService.validatePortionsToAdd(pizza,3L,3)).thenThrow(new ServiceException(400, "error some exception"));
//        pizzaService.validatePortionsToAdd(pizza, 3L, 3);
//
//        try {
//            pizzaService.validatePortionsToAdd(pizza,3L,3);
//            fail();
//        } catch (final ServiceException e) {
//
//        } catch (final Exception e) {
//            fail();
//        }
        when(pizzaRepository.findById(1L)).thenReturn(pizza);


    }
}

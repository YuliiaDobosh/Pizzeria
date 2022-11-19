package lpnu.service.impl;

import lpnu.Application;
import lpnu.dto.AddPizzaToOrderDTO;
import lpnu.entity.*;
import lpnu.entity.enumeration.PizzaSize;
import lpnu.entity.enumeration.UserRole;
import lpnu.exception.ServiceException;
import lpnu.mapper.OrderMapper;
import lpnu.repository.MenuRepository;
import lpnu.repository.OrderRepository;
import lpnu.repository.PizzaRepository;
import lpnu.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class OrderServiceTest {
    @Test
    public void test_addPizzaToOrder_notValidPizzaInMenu() {
        final OrderRepository orderRepository = Mockito.mock(OrderRepository.class);
        final OrderMapper orderMapper = Mockito.mock(OrderMapper.class);
        final TotalPriceServiceImpl totalPriceService = Mockito.mock(TotalPriceServiceImpl.class);
        final PizzaRepository pizzaRepository = Mockito.mock(PizzaRepository.class);
        final MenuRepository menuRepository = Mockito.mock(MenuRepository.class);

        final String userName = "Tom";
        final String userSurname = "Cat";
        final String userEmail = "Tom@gmail.com";

        final User user = new User(1L, userName,
                userSurname, userEmail, UserRole.ADMIN);

        final OrderService orderService = new OrderServiceImpl(orderRepository, orderMapper,
                totalPriceService, pizzaRepository, menuRepository);


        final AddPizzaToOrderDTO addPizzaToOrderDTO = new AddPizzaToOrderDTO(
                1L, 55L, new BigDecimal(300), 2);

        final Order order = new Order();
        order.setId(1L);
        order.setTotalPrice(new BigDecimal(140));
        order.setTotalPriceService(totalPriceService);
        order.setOrders(Stream.of(
                new OrderDetails(new Pizza(
                        "Hawaii", new BigDecimal(140), PizzaSize.SMALL,
                        PizzaSize.SMALL.weight, new HashMap<>(), 9L), 1)
        ).collect(Collectors.toList()));
        order.setUser(user);

        final Pizza pizza = new Pizza();
        pizza.setWeight(PizzaSize.LARGE.weight);
        pizza.setIngredients(new HashMap<>());
        pizza.setId(55L);
        pizza.setName("Margarita");
        pizza.setPrice(new BigDecimal(150));
        pizza.setSize(PizzaSize.MEDIUM);

        final Menu menu = new Menu();
        menu.setAllPizzas(Stream.of(
                new Pizza(
                        "Hawaii", new BigDecimal(140), PizzaSize.SMALL,
                        PizzaSize.SMALL.weight, new HashMap<>(), 9L),
                new Pizza("Margarita", new BigDecimal(130), PizzaSize.MEDIUM,
                        PizzaSize.MEDIUM.weight, new HashMap<>(), 8L),
                new Pizza("Capriciosa", new BigDecimal(150), PizzaSize.LARGE,
                        PizzaSize.LARGE.weight, new HashMap<>(), 10L)
        ).collect(Collectors.toList()));

        when(orderRepository.findById(1L)).thenReturn(order);
        when(pizzaRepository.findById(55L)).thenReturn(pizza);
        when(menuRepository.getMenu()).thenReturn(menu);

        try {
            orderService.addPizzaToOrder(addPizzaToOrderDTO);
            fail();
        } catch (final ServiceException e) {

        } catch (final Exception e) {
            fail();
        }
    }
}

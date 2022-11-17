package lpnu.service.impl;

import lpnu.Application;
import lpnu.dto.AddPizzaToOrderDTO;
import lpnu.entity.Order;
import lpnu.mapper.OrderMapper;
import lpnu.repository.MenuRepository;
import lpnu.repository.OrderRepository;
import lpnu.repository.PizzaRepository;
import lpnu.service.OrderService;
import lpnu.service.TotalPriceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class OrderServiceTest {
    @Test
    public void test_addPizzaToOrder_notValidPizzaInMenu() {
        final OrderRepository orderRepository = Mockito.mock(OrderRepository.class);
        final OrderMapper orderMapper = Mockito.mock(OrderMapper.class);
        final TotalPriceService totalPriceService = Mockito.mock(TotalPriceServiceImpl.class);
        final PizzaRepository pizzaRepository = Mockito.mock(PizzaRepository.class);
        final MenuRepository menuRepository = Mockito.mock(MenuRepository.class);

        final OrderService orderService = new OrderServiceImpl(orderRepository, orderMapper,
                totalPriceService, pizzaRepository, menuRepository);


        final AddPizzaToOrderDTO addPizzaToOrderDTO = new AddPizzaToOrderDTO(
                1L ,2L ,new BigDecimal(300),2);

     when(orderRepository.findById(1L)).thenReturn(new Order());
     when(orderMapper.toDTO(any())).thenCallRealMethod();
    }
}

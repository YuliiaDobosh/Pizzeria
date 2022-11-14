package lpnu.service.impl;

import lpnu.entity.dto.MenuDTO;
import lpnu.entity.dto.PizzaDTO;
import lpnu.entity.Pizza;
import lpnu.mapper.MenuMapper;
import lpnu.mapper.PizzaMapper;
import lpnu.repository.MenuRepository;
import lpnu.repository.PizzaRepository;
import lpnu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private PizzaMapper pizzaMapper;
    public MenuDTO getMenu(){return menuMapper.toDTO(menuRepository.getMenu());}
    @Override
    public MenuDTO create(final PizzaDTO pizzaDTO) {
        return menuMapper.toDTO(menuRepository.save(pizzaMapper.toEntity(pizzaDTO)));
    }

    @Override
    public void delete(final Long id) {
        menuRepository.delete(id);
    }

    @Override
    public PizzaDTO update(final PizzaDTO pizzaDTO) {
        final Pizza pizza = pizzaMapper.toEntity(pizzaDTO);

        menuRepository.update(pizza);

        return pizzaMapper.toDTO(pizza);
    }
    public PizzaDTO findPizzaById(final Long pizzaId) {
        return pizzaMapper.toDTO(menuRepository.findPizzaById(pizzaId));
    }
}
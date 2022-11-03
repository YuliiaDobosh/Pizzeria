package lpnu.service.impl;

import lpnu.dto.BasketDTO;
import lpnu.entity.Basket;
import lpnu.mapper.BasketMapper;
import lpnu.repository.BasketRepository;
import lpnu.repository.PizzaRepository;
import lpnu.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BasketServiceImpl implements BasketService {
    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private BasketMapper basketMapper;

    @Autowired
    private PizzaRepository pizzaRepository;


    @Override
    public List<BasketDTO> getAllBaskets() {
        return basketRepository.getAllBaskets().stream()
                .map(basketMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BasketDTO create(final BasketDTO basketDTO) {
        final Basket basket = basketMapper.toEntity(basketDTO);

        basket.setOrderDateTime(LocalDateTime.now());
        basket.setOrders(new ArrayList<>());

        basketRepository.save(basket);

        return basketMapper.toDTO(basket);
    }

    @Override
    public BasketDTO findById(final Long id) {
        return basketMapper.toDTO(basketRepository.findById(id));
    }

    @Override
    public void delete(final Long id) {
        basketRepository.delete(id);
    }
}

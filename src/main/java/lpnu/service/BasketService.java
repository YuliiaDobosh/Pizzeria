package lpnu.service;

import lpnu.dto.BasketDTO;

import java.util.List;

public interface BasketService {
    List<BasketDTO> getAllBaskets();
    BasketDTO create(BasketDTO orderDTO);
    BasketDTO findById(Long id);

    void delete(Long id);

    //void addItemToOrder(AddItemToOrderDTO addDTO);
}

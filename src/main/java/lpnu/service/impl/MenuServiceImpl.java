package lpnu.service.impl;

import lpnu.dto.MenuDTO;
import lpnu.entity.Menu;
import lpnu.mapper.MenuMapper;
import lpnu.repository.MenuRepository;
import lpnu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuDTO> getAllMenus() {
        return menuRepository.getAllMenus().stream()
                .map(menuMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MenuDTO create(final MenuDTO menuDTO) {

        final Menu menu = menuMapper.toEntity(menuDTO);
        menuRepository.save(menu);

        return menuMapper.toDTO(menu);
    }

    @Override
    public void delete(final Long id) {
        menuRepository.delete(id);
    }

    @Override
    public MenuDTO update(final MenuDTO menuDTO) {
        final Menu menu = menuMapper.toEntity(menuDTO);

        menuRepository.update(menu);

        return menuMapper.toDTO(menu);
    }

    @Override
    public MenuDTO findById(final Long id) {
        return menuMapper.toDTO(menuRepository.findById(id));
    }
}
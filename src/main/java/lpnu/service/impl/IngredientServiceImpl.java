package lpnu.service.impl;

import lpnu.dto.IngredientDTO;
import lpnu.entity.Ingredient;
import lpnu.mapper.IngredientMapper;
import lpnu.repository.IngredientRepository;
import lpnu.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private IngredientMapper ingredientMapper;

    @Override
    public List<IngredientDTO> getAllIngredients() {
        return ingredientRepository.getAllIngredients().stream()
                .map(IngredientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public IngredientDTO create(final IngredientDTO ingredientDTO) {

        final Ingredient ingredient = IngredientMapper.toEntity(ingredientDTO);
        ingredientRepository.save(ingredient);

        return IngredientMapper.toDTO(ingredient);
    }

    @Override
    public void delete(final Long id) {
        ingredientRepository.delete(id);
    }

    @Override
    public IngredientDTO update(final IngredientDTO ingredientDTO) {
        final Ingredient ingredient = IngredientMapper.toEntity(ingredientDTO);

        ingredientRepository.update(ingredient);

        return IngredientMapper.toDTO(ingredient);
    }

    @Override
    public IngredientDTO findById(final Long id) {
        return IngredientMapper.toDTO(ingredientRepository.findById(id));
    }
}
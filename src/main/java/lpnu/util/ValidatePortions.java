package lpnu.util;

import lpnu.entity.Pizza;

import java.util.Map;
import java.util.stream.Collectors;

public class ValidatePortions {
    public static boolean validateIngredientPortions(final Pizza pizza, final Integer portions) {
        final Integer portionLimitToAdd = 1;
        final Integer portionLimitInPizza = 2;
        final Map<Long, Integer> wrongPortions = pizza.getIngredients().entrySet().stream()
                .filter(e->e.getValue() > portionLimitInPizza)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return wrongPortions.size() == 0 && portions.equals(portionLimitToAdd);
    }
}

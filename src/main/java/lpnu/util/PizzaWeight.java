package lpnu.util;

import lpnu.dto.PizzaDTO;
import lpnu.entity.enumeration.PizzaSize;

public class PizzaWeight{
    public static int PizzaWeight(final PizzaDTO pizzaDTO){
        int weightToSet = 0;
        if(pizzaDTO.getSize().equals(PizzaSize.SMALL)){
            weightToSet = 250;
        }
        if(pizzaDTO.getSize().equals(PizzaSize.MEDIUM)){
            weightToSet = 500;
        }
        if(pizzaDTO.getSize().equals(PizzaSize.LARGE)){
            weightToSet = 750;
        }
        return weightToSet;
    }

}

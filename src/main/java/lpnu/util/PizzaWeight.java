package lpnu.util;

import lpnu.entity.Pizza;
import lpnu.entity.enumeration.PizzaSize;

public class PizzaWeight{
    public static int PizzaWeight(final Pizza pizza){
         int weightToSet = 0;
        if(pizza.getSize().equals(PizzaSize.SMALL)){
            weightToSet = 250;
        }
        if(pizza.getSize().equals(PizzaSize.MEDIUM)){
            weightToSet = 500;
        }
        if(pizza.getSize().equals(PizzaSize.LARGE)){
            weightToSet = 750;
        }
        return weightToSet;
    }

}

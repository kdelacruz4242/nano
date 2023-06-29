/**
 *  Lab 6
 *  CS160L-1001
 *  Class Description: This class implements the coffee interface, a black coffee
 *  is one of the two coffee base options that returns its cost, printed string, and
 *  adds this string into an ingredient list
 *  June 28, 2023
 *  @author  Kaye-Angeli Delacruz
 */
import java.util.List;
import java.util.ArrayList;

public class Espresso implements Coffee{

    @Override
    public double getCost(){
        return 1.75;
    }
        @Override
        public List<String> getIngredients(){
            List<String> ingredients = new ArrayList<>();
            ingredients.add("Espresso");
            return ingredients;
        }

    @Override
    public String printCoffee(){
        return " espresso";
    }
}


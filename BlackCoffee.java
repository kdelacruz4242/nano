/**
 *  Lab 6
 *  Class Description: This class implements the coffee interface, an espresso
 *  is one of the two coffee base options that returns its cost, printed string, and
 *  adds this string into an ingredient list
 *  CS160L-1001
 *  June 28, 2023
 *  @author  Kaye-Angeli Delacruz
 */
import java.util.List;
import java.util.ArrayList;

public class BlackCoffee implements Coffee{
    @Override
    public double getCost(){
        return 1.0;
    }

    @Override
    public List<String> getIngredients(){
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Black Coffee");
        return ingredients;
    }

    @Override
    public String printCoffee(){
        return " black coffee";
    }
}

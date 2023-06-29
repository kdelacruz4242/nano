/**
 *  Lab 6
 *  CS160L-1001
 *  Class Description: This class implements the coffee interface, a medium size
 *  is one of the three coffee size options that returns its cost, printed string, and
 *  adds this string into an ingredient list
 *  June 28, 2023
 *  @author  Kaye-Angeli Delacruz
 */
import java.util.ArrayList;
import java.util.List;
public class MediumSize implements Coffee{
    @Override
    public double getCost(){
        return 0.25;
    }
    @Override
    public List<String> getIngredients(){
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Medium Size");
        return ingredients;
    }
    @Override
    public String printCoffee(){
        return "A medium";
    }
}



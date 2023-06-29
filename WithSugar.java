/**
 *  Lab 6
 *  CS160L-1001
 *  Class Description: This class extends the CoffeeDecorator class and uses all
 *  the methods in that class. It returns the cost, ingredient, and string.
 *  June 28, 2023
 *  @author  Kaye-Angeli Delacruz
 */
import java.util.List;

public class WithSugar extends CoffeeDecorator{
    public WithSugar(Coffee c){
        super(c);
    }

    @Override
    public double getCost(){
        return super.getCost() + 0.15;
    }

    @Override
    public List<String> getIngredients(){
        List<String> ingredients = super.getIngredients();
        ingredients.add("Sugar");
        return ingredients;
    }

    @Override
    public String printCoffee(){
        return super.printCoffee() + " with sugar";
    }
}

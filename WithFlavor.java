/**
 *  Lab 6
 *  CS160L-1001
 *  Class Description: This class extends the CoffeeDecorator class and uses all
 *  the methods in that class. It returns the cost, ingredient, and string. This class
 *  includes an enum of 3 specific string choices: Caramel, Mocha, and Vanilla in which
 *  in the getIngredients and printCoffee methods it is able to return the specific
 *  flavor that was selected
 *  June 28, 2023
 *  @author  Kaye-Angeli Delacruz
 */
import java.util.List;
public class WithFlavor extends CoffeeDecorator{

    //The only possible string choices
    enum Syrup{
        caramel,
        mocha,
        vanilla
    }

    private Syrup flavor;

    public WithFlavor(Coffee c, Syrup s){
        super(c);
        flavor = s;
    }

    @Override
    public double getCost(){
        return super.getCost() + 0.35;
    }

    @Override
    public List<String> getIngredients(){
        List<String> ingredients = super.getIngredients();
        switch (flavor){
            case caramel:
                ingredients.add("CARAMEL Syrup");
                break;
            case mocha:
                ingredients.add("MOCHA Syrup");
                break;
            case vanilla:
                ingredients.add("VANILLA Syrup");
                break;
        }
        return ingredients;
    }


    @Override
    public String printCoffee(){
        return super.printCoffee() + " with " + flavor;
    }
}

/**
 *  Lab 6
 *  CS160L-1001
 *  Class Description: This is an abstract class that implements coffee and is the extender for the
 *  following classes: WithFlavor, WithMilk, WithSugar, WithWhippedCream, and
 *  WithHotWater. All the classes mentioned contains all the methods below
 *  June 28, 2023
 *  @author  Kaye-Angeli Delacruz
 */
import java.util.List;

public abstract class CoffeeDecorator implements Coffee{
    private Coffee coffee;

    public CoffeeDecorator(Coffee c){
        coffee = c;
    }
    //Gets and returns the cost of the item/ingredient
    public double getCost(){
        return coffee.getCost();
    }
    //adds this item into ingredients
    public List<String> getIngredients(){
        return null;
    }
    //prints this item with its own set string in their respective classes
    public String printCoffee(){
        return coffee.printCoffee();
    }
}

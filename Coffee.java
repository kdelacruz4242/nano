/**
 *  Lab 6
 *  CS160L-1001
 *  Class Description: This class is the interface for many classes such as:
 *  Espresso, Black Coffee, SmallSize, MediumSize, and LargeSize
 *  June 28, 2023
 *  @author  Kaye-Angeli Delacruz
 */
import java.util.List;

public interface Coffee{
    //Gets and returns the cost of the item/ingredient
    double getCost();
    //adds this item into ingredients
    List<String> getIngredients();
    //prints this item with its own set string in their respective classes
    String printCoffee();
}

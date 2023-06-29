/**
 *  Lab 6
 *  CS160L-1001
 *  Class Description: This class is the one that prints the ordering sheet and also
 *  stores information about the coffee orders, date, to add coffees to the order,
 *  and to get the total amount.
 *  June 28, 2023
 *  @author  Kaye-Angeli Delacruz
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CoffeeOrder{
    private List<Coffee> coffees;
    private LocalDateTime orderDate;

    //Goes into the array of coffees that are made and also gets the Month/Date/Timestamp
    public CoffeeOrder(){
        coffees = new ArrayList<Coffee>();
        orderDate = LocalDateTime.now();
    }
    //The method that gets that made coffee and adds it into the list
    public void addCoffee(Coffee c){
        coffees.add(c);
    }

    //Gets the total for all the coffees in that list
    public double getTotal(){
        double total = 0;
        for (Coffee coffee : coffees){
            total += coffee.getCost();
        }
        return total;
    }

    //Prints the whole order with the date and time, the amount of coffees you ordered,
    //and the total cost of your order using String Builder.
    public String printOrder(){
        StringBuilder order = new StringBuilder("ORDER RECEIPT\n");
        order.append(String.format("Timestamp: %s%n", orderDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mma"))));
        for (int i = 0; i < coffees.size(); i++){
            Coffee coffee = coffees.get(i);
            order.append(String.format("Item %d: %s - %.2f%n", i + 1, coffee.printCoffee(), coffee.getCost()));
        }
        order.append(String.format("TOTAL = %.2f%n", getTotal()));
        return order.toString();
    }
}

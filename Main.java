/**
 *  Lab 6
 *  Class Description: Main class that will read from user input and make the
 *  coffee orders and implements inventory for ingredients. This class prompts the
 *  user options and can also build coffee orders.mMain will not terminate as
 *  long as the user is finished with its coffee order. This class
 *  also has all the options in its methods such as: writeOrderLog, writeInventory,
 *  readInventory, and isInInventory to check the inventory availability.
 *  CS160L-1001
 *  June 28, 2023
 *  @author  Kaye-Angeli Delacruz
 */
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class Main{
    private static Map<String, Integer> inventory = new TreeMap<String, Integer>();
    private static List<CoffeeOrder> orders = new ArrayList<CoffeeOrder>();
    private static String logFile = "OrderLog.txt";
    private static String inventoryFile = "Inventory.txt";

    public static void main(String[] args){
        System.out.println("Welcome to Java Coffee Co.!");
        readInventory(inventoryFile);
        try (Scanner input = new Scanner(System.in)){
            boolean menuExit = false;
            while (!menuExit){
                System.out.println("Menu Options:");
                System.out.println("\t1. New Order");
                System.out.println("\t2. Reload Inventory");
                System.out.println("\t3. Update Inventory");
                System.out.println("\t4. Update Order Log");
                System.out.println("\t5. Exit Application");

                int choices;
                if (input.hasNextInt()){
                    choices = input.nextInt();
                    input.nextLine();
                } else{
                    System.out.println("Please enter a valid number.");
                    input.nextLine();
                    continue;
                }

                switch (choices){
                    case 1:
                        CoffeeOrder order = buildOrder();
                        orders.add(order);
                        System.out.println(order.printOrder());
                        break;
                    case 2:
                        readInventory(inventoryFile);
                        break;
                    case 3:
                        writeInventory(inventoryFile);
                        break;
                    case 4:
                        writeOrderLog(logFile);
                        break;
                    case 5:
                        writeOrderLog(logFile);
                        menuExit = true;
                        break;
                    default:
                        System.out.println("Please enter a number from 1 to 5.");
                        break;
                }
            }
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static CoffeeOrder buildOrder(){
        CoffeeOrder order = new CoffeeOrder();
        try {
            Scanner input = new Scanner(System.in);
            boolean addCoffee = true;
            while (addCoffee){
                    System.out.println("Select coffee size:");
                    System.out.println("\t1. Small Size");
                    System.out.println("\t2. Medium Size");
                    System.out.println("\t3. Large Size");
                    Coffee coffee = null;

                    int option2 = input.nextInt();
                    input.nextLine();
                    switch (option2){
                        case 1 -> coffee = new SmallSize();
                        case 2 -> coffee = new MediumSize();
                        case 3 -> coffee = new LargeSize();
                        default -> System.out.println("Please enter valid option.");
                    }

                    System.out.println("Select coffee type:");
                    System.out.println("\t1. Black Coffee");
                    System.out.println("\t2. Espresso");

                    int option = 0;
                    while (option < 1 || option > 2){
                     if (!input.hasNextInt()){
                        System.out.println("Please enter a valid number.");
                        input.nextLine();
                    } else{
                        option = input.nextInt();
                        if (option < 1 || option > 2) System.out.println("Please enter a valid option.");
                    }
                }
                input.nextLine();
                if (option == 2) {
                    if (isInInventory("Espresso")){
                        coffee = new Espresso();
                        inventory.remove("Espresso", 1);
                    } else{
                        System.out.println("Sorry, no Espresso available.");
                    }
                } else{
                    if (isInInventory("Black Coffee")){
                        coffee = new BlackCoffee();
                        inventory.remove("Black Coffee", 1);
                    } else{
                        System.out.println("Sorry, no Black Coffee available.");
                    }
                }

                while (option != 0){
                    System.out.println(String.format("Coffee brewing: %s.", coffee.printCoffee()));
                    System.out.println("Would you like to add anything to your coffee?");
                    System.out.println("\t1. Flavored Syrup");
                    System.out.println("\t2. Hot Water");
                    System.out.println("\t3. Milk");
                    System.out.println("\t4. Sugar");
                    System.out.println("\t5. Whipped Cream");
                    System.out.println("\t0. NO - Finish Coffee");

                    while (!input.hasNextInt()){
                        System.out.println("Please enter a valid number.");
                        input.nextLine();
                    }
                    option = input.nextInt();
                    input.nextLine();
                    coffee = switch (option){
                        case 1 ->{
                            System.out.println("Please select a flavor:");
                            for (WithFlavor.Syrup flavor : WithFlavor.Syrup.values()){
                                System.out.println("\t" + String.format("%d. %s", flavor.ordinal() + 1, flavor));
                            }
                            int max = WithFlavor.Syrup.values().length;
                            option = 0;
                            while (option < 1 || option > max){
                                if (!input.hasNextInt()) {
                                    System.out.println("Please enter a valid number.");
                                    input.nextLine();
                                } else{
                                    option = input.nextInt();
                                    if (option < 1 || option > max) System.out.println("Please enter a valid option.");
                                }
                            }
                            input.nextLine();
                            WithFlavor.Syrup flavor = WithFlavor.Syrup.values()[option - 1];
                            option = 1;
                            yield new WithFlavor(coffee, flavor);
                        }
                        case 2 ->{
                            if (isInInventory("Hot Water")){
                                    inventory.remove("Hot Water", 1);
                                yield new WithHotWater(coffee);
                            } else{
                                System.out.println("Sorry, no hot water available.");
                                yield coffee;
                                }
                        }
                        case 3 ->{
                            if (isInInventory("Milk")){
                                inventory.remove("Milk", 1);
                                yield new WithMilk(coffee);
                            } else{
                                System.out.println("Sorry, no milk available.");
                                yield coffee;
                            }
                        }
                        case 4 ->{
                            if (isInInventory("Sugar")) {
                                inventory.remove("Sugar", 1);
                                yield new WithSugar(coffee);
                            } else {
                                System.out.println("Sorry, no sugar available.");
                                yield coffee;
                            }
                        }
                        case 5 ->{
                            if(isInInventory("Whipped Cream")){
                                inventory.remove("Whipped Cream", 1);
                                yield new WithWhippedCream(coffee);
                            } else{
                                System.out.println("Sorry, no whipped cream available.");
                                yield coffee;
                            }
                        }
                        default ->{
                            if (option != 0) System.out.println("Please enter valid option.");
                            yield coffee;
                        }
                    };
                }
                order.addCoffee(coffee);

                System.out.println("Would you like to order another coffee (Y or N)?");
                String yn = input.nextLine();
                while (!(yn.equalsIgnoreCase("N") || yn.equalsIgnoreCase("Y"))) {
                    System.out.println("Please enter Y or N.");
                    yn = input.nextLine();
                }
                addCoffee = !yn.equalsIgnoreCase("N");
            }
        } catch (Exception e){
            System.out.println("Error building order: " + e.getMessage());
        }
        return order;
    }

    private static Map<String, Integer> readInventory(String filePath){
        try (BufferedReader reader = new BufferedReader(new FileReader(inventoryFile))) {
        String line;
        while ((line = reader.readLine()) != null){
            String[] parts = line.split("=");
            String ingredient = parts[0].trim();
            int quantity = Integer.parseInt(parts[1].trim());
            inventory.put(ingredient, quantity);
        }
        reader.close();
        System.out.println("Current Inventory:");
        System.out.println(inventory);
    } catch (Exception e){
        System.out.println("Error loading inventory: " + e.getMessage());
        }
        return null;
    }

    private static void writeInventory(String filePath){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inventoryFile))) {
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                String ingredient = entry.getKey();
                int quantity = entry.getValue();
                writer.write(ingredient + " = " + quantity);
                writer.newLine();
            }
            writer.close();
            System.out.println("Inventory updated successfully.");
        } catch (Exception e){
            System.out.println("Error writing inventory: " + e.getMessage());
        }
    }

    private static void writeOrderLog(String filePath){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (CoffeeOrder order : orders){
                writer.write(order.printOrder());
                writer.newLine();
            }
            orders.clear();
            System.out.println("Order logged successfully.");
        } catch (Exception e){
            System.out.println("Error writing order log: " + e.getMessage());
        }
    }

    private static boolean isInInventory(String i){
        return inventory.containsKey(i) && inventory.get(i) > 0;
    }

}
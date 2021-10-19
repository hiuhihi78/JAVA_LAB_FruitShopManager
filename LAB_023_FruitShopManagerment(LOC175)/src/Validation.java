
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
class Validation {
    
    public static boolean checkFruitExistedById(ArrayList<Fruit> listFruitForOwner, String id) {
        for(Fruit fruit : listFruitForOwner){
            if(fruit.getId().equalsIgnoreCase(id)){
                return true;
            }
        }
        return false;
    }

    public static boolean checkFruitExistedByNamePriceOrigin(ArrayList<Fruit> listFruitForBuyer, String name, double price, String origin) {
        for (Fruit fruit : listFruitForBuyer) {
            if (fruit.getName().equalsIgnoreCase(name) && fruit.getPrice() == price
                    && fruit.getOrigin().equalsIgnoreCase(origin)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkYesNo(String msg) {
        String choice = null;
        // loop until user input correct
        while (true) {
            System.out.println("Do you want to " + msg + "(Y/N)?");
            choice = GetValue.getInputString("your choice");
            if (choice.equalsIgnoreCase("y")) {
                return true;
            }
            if (choice.equalsIgnoreCase("n")) {
                return false;
            }
            System.err.println("Invalid of choice, you must enter Y/y or N/n!");
        }
    }

   
}

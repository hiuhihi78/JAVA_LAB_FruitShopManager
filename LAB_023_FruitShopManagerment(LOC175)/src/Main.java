
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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Fruit> listFruitForOwner = new ArrayList<>();
        ArrayList<Fruit> listFruitForBuyer = new ArrayList<>();
        ArrayList<Invoice> listCustomerInvoice = new ArrayList<>();
        //loop until user want to exit
        while (true) {
            // display menu
            Manager.displayMenu();
            // choice option
            int choice = GetValue.getIntNumberInRange(1, 4, "your choie");
            switch (choice) {
                case 1:
                    // create product
                    Manager.createFruit(listFruitForOwner, listFruitForBuyer);
                    break;
                case 2:
                    // view orders
                    Manager.viewOrders(listCustomerInvoice);
                    break;
                case 3:
                    // Shopping for buyer
                    Manager.shoppingForBuyer(listCustomerInvoice, listFruitForBuyer);
                    break;
                case 4:
                    
            }
        }
    }

}

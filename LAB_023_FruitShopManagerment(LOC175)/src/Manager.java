
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
class Manager {

    public static void displayMenu() {
        System.out.println("FRUIT SHOP SYSTEM");
        System.out.println("         1.Create Fruit");
        System.out.println("         2.View orders");
        System.out.println("         3.Shopping (for buyer)");
        System.out.println("         4.Exit");
        System.out.println("(Please choose 1 to create product, 2 to view order, 3 for shopping, 4 to Exit program).");
    }

    public static void createFruit(ArrayList<Fruit> listFruitForOwner, ArrayList<Fruit> listFruitForBuyer) {
        // loop until user want to exit
        while (true) {
            System.out.println("CREATE FRUIT");
            String id = GetValue.getIdFruit(listFruitForOwner);
            String name = GetValue.getInputString("fruit name");
            double price = GetValue.getInputPositiveDouble("price");
            int quantity = GetValue.getInputPositiveInt("quantity");
            String origin = GetValue.getInputString("origin");
            // add fruit into listFruitForOwner
            listFruitForOwner.add(new Fruit(id, name, price, quantity, origin));
            // add fruit into listFruitForBuyer
            // check fruit have same name, price, origin existed in listFruitForBuyer
            boolean fruitExisted = 
                    Validation.checkFruitExistedByNamePriceOrigin(listFruitForBuyer, name, price, origin);
            if (fruitExisted == true) {
                Fruit fruit =
                        GetValue.getFruitByNamePriceOrigin(listFruitForBuyer, name, price, origin);
                int quantityBefore = fruit.getQuantity();
                fruit.setQuantity(quantityBefore + quantity);
            } else {
                listFruitForBuyer.add(new Fruit(null, name, price, quantity, origin));
            }
            // check continue create fruit
            boolean continueCreate = Validation.checkYesNo("continue");
            if (continueCreate == true) {
                System.out.println("");
                continue;
            } else {
                System.out.println("");
                return;
            }
        }
    }

    public static void viewOrders(ArrayList<Invoice> listCustomerInvoice) {
        System.out.println("VIEW ORDERS");
        if (listCustomerInvoice.isEmpty()) {
            System.out.println("System is empty!\n");
        }
        // traverse all element of listCustomerInvoice to display invoice
        for (Invoice customerInvoice : listCustomerInvoice) {
            String nameCustomer = customerInvoice.getNameCustomer();
            ArrayList<Fruit> listOrder = new ArrayList<>();
            listOrder.addAll(customerInvoice.getListOrder());
            double totalAmount = 0;
            System.out.println("Customer: " + nameCustomer);
            System.out.println(" Product | Quantity | Price | Amount");
            //traverse all element of listOrder to display fruit ordered
            for (int i = 0; i < listOrder.size(); i++) {
                int id = i + 1;
                String product = (id + ". " + listOrder.get(i).getName());
                int quantity = listOrder.get(i).getQuantity();
                double price = listOrder.get(i).getPrice();
                double amount = listOrder.get(i).getAmount();
                totalAmount = totalAmount + amount;
                System.out.printf(" %-13s%-7d%-9.1f%.1f$\n", product, quantity, price, amount);
            }
            System.out.printf("Total: %.1f$\n\n", totalAmount);
        }
    }


public static void shoppingForBuyer(ArrayList<Invoice> listInvoice, ArrayList<Fruit> listFruitForBuyer) {
        System.out.println("SHOPPING FRUIT");
        boolean makeInvoice = false;
        // create list fruit order of customer
        ArrayList<Fruit> listFruitOrder = new ArrayList<>();
        // check sold out all fruit
        if (listFruitForBuyer.isEmpty()) {
            System.out.println("Sorry! We are sold out all fruit today!\n");
            return;
        }
        // loop until user want to exit
        while (true) {
            // check sold out all fruit
            if (listFruitForBuyer.isEmpty()) {
                System.out.println("Sorry! We are sold out all fruit today!\n");
                // display product ordered
                GetValue.getDisplayAllFruitOdered(listFruitOrder);
                break;
            }
            // display list fruit for buyer to order
            GetValue.getDisplayFruitToOrder(listFruitForBuyer);
            // get item fruit order
            Fruit fruitOrder = GetValue.getFruitByNumberItem(listFruitForBuyer);
            System.out.println("You selected: " + fruitOrder.getName());
            // get quantity fruit order
            int quantityFruitOrder = GetValue.getQuantityFruitOrder(fruitOrder);
            // check user buying fruit
            if (quantityFruitOrder == 0) {// not buying
                System.out.println("You not buying " + fruitOrder.getName() + "!");
                makeInvoice = Validation.checkYesNo("order now");
                if (makeInvoice == true) {
                    if(!listFruitOrder.isEmpty()){ // order before
                    // display all fruit ordered
                    GetValue.getDisplayAllFruitOdered(listFruitOrder);
                    }
                    break;
                } else {
                    continue;
                }
            } else {// buying
                String nameFruitOrder = fruitOrder.getName();
                double priceFruitOrder = fruitOrder.getPrice();
                String originFruitOrder = fruitOrder.getOrigin();
                // update quantity fruit after order of listFruitForBuyer
                Fruit fruitListBuyer = 
                        GetValue.getFruitByNamePriceOrigin(
                                listFruitForBuyer, nameFruitOrder, priceFruitOrder, originFruitOrder);
                fruitListBuyer.setQuantity(fruitListBuyer.getQuantity() - quantityFruitOrder);
                // check fruit sold out -> remove
                if (fruitListBuyer.getQuantity() == 0) {
                    listFruitForBuyer.remove(fruitListBuyer);
                }
                // check fruit was ordered in listFruitOder
                boolean fruitOrdered = Validation.checkFruitExistedByNamePriceOrigin(
                        listFruitOrder, nameFruitOrder, priceFruitOrder, originFruitOrder);
                if (fruitOrdered == true) {// oredered before
                    Fruit fruitListOrder = GetValue.getFruitByNamePriceOrigin(
                            listFruitOrder, nameFruitOrder, priceFruitOrder, originFruitOrder);
                    fruitListOrder.setQuantity(fruitListOrder.getQuantity() + quantityFruitOrder);
                } else {// frist time order
                    listFruitOrder.add(
                            new Fruit(null, nameFruitOrder, priceFruitOrder, quantityFruitOrder, originFruitOrder));
                }
                // check user want to make invoice
                makeInvoice = Validation.checkYesNo("oder now");
                if (makeInvoice == true) {
                    // display all fruit ordered
                    GetValue.getDisplayAllFruitOdered(listFruitOrder);
                    break;
                } else {
                    continue;
                }
            }
        }
        // check customer not buying in case fruits not sold out
        if (listFruitOrder.isEmpty() && !listFruitForBuyer.isEmpty()) {
            System.out.println("You not buy anything!\n");
            return;
        }
//        // check customer not buying  fruit 
//        if (listFruitOrder.isEmpty()) {
//            return;
//        }

        // input name customer
        String nameCustomer = GetValue.getInputString("your name");
        // add new invoice in to listInvoice
        listInvoice.add(new Invoice(nameCustomer, listFruitOrder));
        listFruitOrder.removeAll(listFruitOrder); // remove for new event
        System.out.println("Successfully!\n");
    }
}

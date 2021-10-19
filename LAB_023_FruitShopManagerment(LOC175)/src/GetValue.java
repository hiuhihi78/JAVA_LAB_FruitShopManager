
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
class GetValue {

    private static final Scanner scanner = new Scanner(System.in);

    public static int getIntNumberInRange(int from, int to, String msg) {
        int result = 0;
        // loop until user input correct
        while (true) {
            result = getInputPositiveInt(msg);
            if (result < from || result > to) {
                System.err.println("Invalid of " + msg + ", " + msg
                        + " must be in range [" + from + " - " + to + "]!");
            } else {
                return result;
            }
        }
    }

    public static String getInputString(String msg) {
        String result = null;
        // loop until user input correct
        while (true) {
            System.out.print("Enter " + msg + ": ");
            result = scanner.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Invalid of " + msg + ", " + msg + " must be not empty!");
            } else {
                return result;
            }
        }
    }

    public static int getInputPositiveInt(String msg) {
        int result = 0;
        // loop until user input correct
        while (true) {
            try {
                String temp = getInputString(msg);
                result = Integer.parseInt(temp);
                if (result < 0) {
                    System.err.println("Invalid of " + msg + ", " + msg + " must be positive interger!");
                    continue;
                } else if (result == 0) {
                    System.err.println("Invalid of " + msg + ", " + msg + " must be greater than 0!");
                } else {
                    return result;
                }
            } catch (Exception e) {
                System.err.println("Invalid of " + msg + ", " + msg + " must be positive interger!");
            }
        }
    }

    public static double getInputPositiveDouble(String msg) {
        double result = 0;
        // loop until user input correct
        while (true) {
            try {
                String temp = getInputString(msg);
                result = Double.parseDouble(temp);
                if (result < 0) {
                    System.err.println("Invalid of " + msg + ", " + msg + " must be a postive number!");
                } else {
                    return result;
                }
            } catch (Exception e) {
                System.err.println("Invalid of " + msg + ", " + msg + " must be a number!");
            }
        }
    }

    public static String getIdFruit(ArrayList<Fruit> listFruitForOwner) {
        String id = null;
        // loop until user input corect
        while (true) {
            // get id
            id = getInputString("id");
            // check fruite exited
            boolean fruiteExisted = Validation.checkFruitExistedById(listFruitForOwner, id);
            if (fruiteExisted == false) {
                return id;
            } else {
                System.err.println("Invalid of fruit id, this fruit id was exited!");
            }
        }
    }

    public static Fruit getFruitByNamePriceOrigin(ArrayList<Fruit> listFruitForBuyer, String name, double price, String origin) {
        for (Fruit fruit : listFruitForBuyer) {
            if (fruit.getName().equalsIgnoreCase(name) && fruit.getPrice() == price
                    && fruit.getOrigin().equalsIgnoreCase(origin)) {
                return fruit;
            }
        }
        return null;
    }

    public static void getDisplayFruitToOrder(ArrayList<Fruit> listFruitForBuyer) {
        System.out.println("| ++ Item ++ | ++ Fruit Name ++ | ++ Origin ++ | ++ Price ++ |");
        int item = 0;
        for (Fruit fruit : listFruitForBuyer) {
            item = item + 1;
            String fruitName = fruit.getName();
            String origin = fruit.getOrigin();
            double price = fruit.getPrice();
            String fruitOrder = String.format("%-14d%-20s%-19s%.1f$", item, fruitName, origin, price);
            System.out.println(fruitOrder);
        }
    }

    public static Fruit getFruitByNumberItem(ArrayList<Fruit> listFruitForBuyer) {
        // loop until user input correct
        while (true) {
            // select number
            int selectNumber = getInputPositiveInt("number item");
            // check fruit with number item exist
            if (selectNumber > listFruitForBuyer.size()) {
                System.err.println("Invalid of number item, number item mus be in range ("
                        + 0 + " - " + listFruitForBuyer.size() + "]");
            } else {
                return listFruitForBuyer.get(selectNumber - 1);
            }
        }
    }

    public static int getQuantityFruitOrder(Fruit fruitOrder) {
        while (true) {
            // get quantity
            System.out.print("Please input quantity: ");
            String temp = scanner.nextLine().trim();
            int quantityOrder = 0;
            if (temp.isEmpty()) {
                System.err.println("Invalid of quantity, quantity must not empty!");
                continue;
            }
            try {
                quantityOrder = Integer.parseInt(temp);
                if (quantityOrder < 0) {
                    System.err.println("Invalid of quantity, quantity must be a positive interger!");
                    continue;
                }
            } catch (Exception e) {
                System.err.println("Invalid of quantity , quantity must be a positive interger!");
                continue;
            }
            // check quantity fruit in system is enough fruit to order
            int fruitQuantity = fruitOrder.getQuantity();
            if (quantityOrder > fruitQuantity) {
                String nameFruit = fruitOrder.getName();
                System.err.println("Not enough " + nameFruit + " to order!"
                        + " Quantity must be in range [0 - " + fruitQuantity + "]!");
                continue;
            } else {
                return quantityOrder;
            }
        }
    }

    public static void getDisplayAllFruitOdered(ArrayList<Fruit> listFruitOrder) {
        System.out.println(" Product | Quantity | Price | Amount");
        double totalAmount = 0;
        for (int i = 0; i < listFruitOrder.size(); i++) {
            String name = listFruitOrder.get(i).getName();
            int quantity = listFruitOrder.get(i).getQuantity();
            double price = listFruitOrder.get(i).getPrice();
            double amount = listFruitOrder.get(i).getAmount();
            System.out.printf(" %-13s%-7d%-9.1f$%.1f$\n", name, quantity, price, amount);
            totalAmount = totalAmount + amount;
        }
        System.out.printf("Total: %.1f$\n", totalAmount);
    }

    public static ArrayList<Fruit> unionList(ArrayList<Fruit> listOrderedBefore, ArrayList<Fruit> listOrder) {
        ArrayList<Fruit> result = new ArrayList<>();
        
        // traverse all element of listOrderedBefore 
        //if have same obj in listOrder -> union quality and add new fruit in result
        // if not same add obj in result
        for(int i = 0; i < listOrderedBefore.size(); i++){
            Fruit fruitOrderBefore = listOrderedBefore.get(i);
            String name = listOrderedBefore.get(i).getName();
            double price = listOrderedBefore.get(i).getPrice();
            int quantityListFruitOrder = listOrderedBefore.get(i).getQuantity();
            String origin = listOrderedBefore.get(i).getOrigin();
            // check fruit in listOrder was ordered before
            boolean fruitExisted = Validation.checkFruitExistedByNamePriceOrigin(listOrder, name, price, origin);
            if(fruitExisted == true){
                Fruit fruitOrder = getFruitByNamePriceOrigin(listOrder, name, price, origin);
                int newQuanlity = quantityListFruitOrder + fruitOrder.getQuantity();
                result.add(new Fruit(null, name, price, newQuanlity, origin));
            }else{
                result.add(fruitOrderBefore);
            }
        }
        // traveser all element of listOrder if have obj not same in listOrderedBefore -> add result
        for(int i = 0; i < listOrder.size(); i++){
            Fruit fruitOrder = listOrder.get(i);
            String name = fruitOrder.getName();
            double price = fruitOrder.getPrice();
            String origin = fruitOrder.getOrigin();
            if(!Validation.checkFruitExistedByNamePriceOrigin(listOrder, name, price, origin)){
                result.add(fruitOrder);
            }
        }
        return result;
    }
}

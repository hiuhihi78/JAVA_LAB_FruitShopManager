
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
public class Invoice {
    private String nameCustomer;
    private ArrayList<Fruit> listOrder = new ArrayList<>();

    public Invoice(String nameCustomer, ArrayList<Fruit> listFruit) {
        this.nameCustomer = nameCustomer;
        this.listOrder.addAll(listFruit);
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public ArrayList<Fruit> getListOrder() {
        return listOrder;
    }

    public void setListOrder(ArrayList<Fruit> listOrder) {
        this.listOrder = listOrder;
    }
    
    
    
}

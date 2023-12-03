package uwu;

import java.util.LinkedList;

import uwu.control.client.ClientController;
import uwu.model.Method;
import uwu.model.Order;
import uwu.model.Product;
import uwu.model.Request;
import uwu.view.App;

public class AppMain {
    public static void main(String[] args) {
        ClientController client = new ClientController();
        Order<Product> test = new Order<Product>(); 
        App.main(args);
    }
}

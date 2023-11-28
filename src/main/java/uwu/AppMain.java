package uwu;

import java.util.LinkedList;

import uwu.control.client.ClientController;
import uwu.model.Method;
import uwu.model.Order;
import uwu.model.Product;
import uwu.model.Request;

public class AppMain {
    public static void main(String[] args) {
        ClientController client = new ClientController();
        Order<Product> test = new Order<Product>();

        //TEST
        test.add(new Product("boh", 0));
        test.add(new Product("ciaoo", 11));
        client.sendRequest(new Request(Method.ADD, null, test));


        App.main(args);
    }
}

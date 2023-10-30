import controller.client.Client;
import model.Method;
import model.Order;
import model.Request;
import view.client.ClientFrame;

public class ClientMain {
    public static void main(String[] args) { 
        new ClientFrame(new Client());
    }
}

package view.client;

import controller.client.Client;
import model.Method;
import model.Order;
import model.Request;

public class ClientFrame {
    private Client client;

    public ClientFrame(Client client) {
        this.client = client;
        client.sendRequest(new Request(Method.ADD, "clientID", new Order("ciao")));
        client.sendRequest(new Request(Method.ADD, "clientID", new Order("AAAA")));
        client.sendRequest(new Request(Method.END));
    }
}

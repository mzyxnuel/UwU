package view.server;

import controller.server.ServerController;
import model.Orders;

public class ServerFrame {
    private ServerController server;
    private Orders orders;

    public ServerFrame(ServerController server) {
        this.server = server;
        this.orders = server.getOrders().getAll();
    }
}

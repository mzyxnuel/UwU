package uwu.model;

import java.util.HashMap;

public class Orders extends HashMap<String, Order> {
    public void add(Request request) {
        put(request.getClientID(), request.getOrder());
    }

    public void update(Request request) {
        replace(request.getClientID(), request.getOrder());
    }   

    public void delete(Request request) {
        remove(request.getClientID());
    }
}

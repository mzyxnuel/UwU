package model;

import java.io.Serializable;

public class Request implements Serializable {
    private Method method;
    private String clientID;
    private Order order;
    
    public Request(Method method, String clientID, Order order) {
        this.method = method;
        this.order = order;
    }

    public Request(Method method) {
        this.method = method;
    }

    public Method getMethod() {
        return method;
    }

    public String getClientID() {
        return clientID;
    }

    public Order getOrder() {
        return order;
    }
    
}

package model;

import java.io.Serializable;

public class Request implements Serializable {
    private Method method;
    private String id;
    private Order order;
    
    
    public Request(Method method, String id, Order order) {
        this.method = method;
        this.order = order;
    }

    public Request(Method method) {
        this.method = method;
    }

    public Method getMethod() {
        return method;
    }

    public String getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }
    
}

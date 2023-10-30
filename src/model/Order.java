package model;

import java.io.Serializable;
import java.util.LinkedList;

public class Order implements Serializable {
    private String boo;

    public Order(String boo) {
        this.boo = boo;
    }

    public String getBoo() {
        return boo;
    }
    
}

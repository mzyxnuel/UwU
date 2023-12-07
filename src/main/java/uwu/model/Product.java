package uwu.model;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private int qty;
    
    public Product(String name, int qty) {
        this.name = name;
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }    

    @Override
    public String toString() {
        return "[name = " + name + ", qty = " + qty + "]";
    }
}

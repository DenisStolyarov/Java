package DAO;

import java.io.Serializable;

public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String price;
    private int id;

    public Item(){}

    public Item(String name, String price, int id){
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

}

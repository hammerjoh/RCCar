package de.hammer.rccar;

public class Datenbank {
    private String product;
    private int quantity;
    private long id;


    public Datenbank(String product, int quantity, long id) {
        this.product = product;
        this.quantity = quantity;
        this.id = id;
    }


    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        String output = quantity + " x " + product;

        return output;
    }
}

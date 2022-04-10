package product;

import utility.Font;

import java.text.DecimalFormat;

public class Product implements Cloneable  {
    //Declare data member
    private String name;
    private double price;
    private int qty;
    private int rating;
    private double totalPrice;//for cart use

    private static int productCount = 1;

    DecimalFormat df2 = new DecimalFormat("0.00");

    public Product(){
        this("",0,0,0);
    }

    public Product(String name, double price, int qty){
        this(name,price,qty,0);
    }

    
    public Product(String name, double price, int qty,int rating) {
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.rating = rating;
        productCount++;
    }

    //Getter and Setter
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return this.rating;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    //Method
    //Convert rating to symbol
    public String convertRating(int rating){
        return switch (rating) {
            case 0 -> "☆☆☆☆☆";
            case 1 -> "★☆☆☆☆";
            case 2 -> "★★☆☆☆";
            case 3 -> "★★★☆☆";
            case 4 -> "★★★★☆";
            case 5 -> "★★★★★";
            default -> "";
        };
    }

    //toString
    public String toString(){
        if(this.totalPrice == 0) {
            //For product use
            return  String.format("%-15s",this.name) + "  " + String.format("%10s", ("RM" + df2.format(this.price))) + "  " + String.format("%10s", this.qty) + "  " + String.format("%10s",convertRating(this.rating));
        } else {
            //For cart use
            return  String.format("%5s"," ") + String.format("%-21s",this.name) + String.format("%1s"," ") + String.format("%-13s", ("RM" + df2.format(this.price))) + " " + String.format("%-6s", this.qty) + "  " + String.format("%-23s", Font.TEXT_CYAN + convertRating(this.rating) + Font.RESET) + "  " + String.format("%-10s", "RM" + df2.format(this.totalPrice));
        }
    }
    //ToEqual
    @Override
    public boolean equals(Object object) {
       return true;
    }

    @Override
    public Product clone() {
        try {
            Product clone = (Product) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }



}







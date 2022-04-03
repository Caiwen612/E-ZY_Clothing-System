public class Product implements Cloneable{
    //Declare data member
    private String name;
    private double price;
    private int qty;
    private int rating;

    private static int productCount = 1;

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

    public int getRating() {
        return this.rating;
    }

    public double getPrice() {
        return this.price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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
        return "\t\t" + this.name + "\t\t\t\t" + "RM" + this.price + "\t\t\t\t" + this.qty + "\t\t\t\t" + convertRating(this.rating);
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

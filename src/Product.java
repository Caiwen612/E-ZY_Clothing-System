public class Product {
    //Declare data member
    private String name;
    private double price;
    private int qty;
    private String description;
    private String rating;

    private static int productCount = 1;

    public Product(){
        this("",0,0,"","");
    }

    public Product(String name, double price, int qty){
        this(name,price,qty,"","");
    }


    public Product(String name, double price, int qty, String description,String rating) {
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.description = description;
        this.rating = rating;
        productCount++;
    }

    //Getter and Setter

    public String getName() {
        return name;
    }

    //Method

    //toString
    public String toString(){
        return "\t\t" + this.name + "\t\t\t\t" + "RM" + this.price + "\t\t\t\t" + this.qty;
    }
    //ToEqual


    @Override
    public boolean equals(Object object) {
       return true;
    }

}

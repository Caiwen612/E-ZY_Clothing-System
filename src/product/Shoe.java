package product;

public class Shoe extends Product implements Cloneable{
    //Declare data members
    private String shoeID;
    private static int shoeCount = 1;

    //No-argh Constructor (Best Practice)
    public Shoe(){
        this("",0,0,0);
    }

    //Parameterized Constructor
    public Shoe(String name, double price, int qty, int rating) {
        super(name, price, qty, rating);
        this.shoeID = generateID();
        shoeCount++;
    }

    //Getter
    public String getShoeID() {
        return shoeID;
    }

    //Method to generate ID
    private static String generateID(){
        return "S" + shoeCount;
    }

    //To String
    @Override
    public String toString() {
        if(this.getTotalPrice() == 0){
            //Product
            return String.format("%25s",this.shoeID) + String.format("%8s","")  + super.toString();
        } else{
            //Cart
            return String.format("%9s",this.shoeID) + super.toString();
        }
    }

    //Clone
    @Override
    public Shoe clone() {
        return (Shoe) super.clone();
    }
}

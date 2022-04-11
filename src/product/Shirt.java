package product;

public class Shirt extends Product implements Cloneable {
    //Declare data members
    private String shirtID;
    private static int shirtCount = 1;

    //No-argh Constructor (Best Practice)
    public Shirt(){
        this("",0,0,0);
    }

    //Parameterized Constructor
    public Shirt(String name, double price, int qty, int rating) {
        super(name, price, qty, rating);
        this.shirtID = generateID();
        shirtCount++;
    }

    //Getter
    public String getShirtID() {
        return this.shirtID;
    }

    //Method to generate ID
    private static String generateID(){
        return "T" + shirtCount;
    }

    //ToString
    @Override
    public String toString() {
        if(this.getTotalPrice() == 0){
            //Product
            return String.format("%25s",this.shirtID) + String.format("%8s","")  + super.toString();
        } else{
            //Cart
            return String.format("%9s",this.shirtID) + super.toString();
        }
    }


    //Clone
    @Override
    public Shirt clone() {
        return (Shirt) super.clone();
    }
}

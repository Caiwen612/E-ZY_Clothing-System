package product;

public class Pant extends Product implements Cloneable{
    //Declare data members
    private String pantID;
    private static int pantCount = 1;

    //No-argh Constructor (Best Practice)
    public Pant(){
        this("",0,0,0);
    }

    //Parameterized constructor
    public Pant(String name, double price, int qty, int rating) {
        super(name, price, qty, rating);
        this.pantID = generateID();
        pantCount++;
    }

    //Getter
    public String getPantID() {
        return this.pantID;
    }

    //Method to generate ID
    private static String generateID(){
        return "P" + pantCount;
    }

    //ToString
    @Override
    public String toString() {
        if(this.getTotalPrice() == 0){
            //Product
            return String.format("%25s",this.pantID) + String.format("%8s","")  + super.toString();
        } else{
            //Cart
            return String.format("%9s",this.pantID) + super.toString();
        }
    }

    //Clone
    @Override
    public Pant clone() {
        return (Pant) super.clone();
    }
}

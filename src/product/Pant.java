package product;

public class Pant extends Product implements Cloneable{
    private String pantID;
    private static int pantCount = 1;

    public Pant(){

    }

    public Pant(String name, double price, int qty) {
        super(name, price, qty);
        this.pantID = generateID();
        pantCount++;
    }

    public Pant(String name, double price, int qty, int rating) {
        super(name, price, qty, rating);
        this.pantID = generateID();
        pantCount++;
    }

    //Setter and getter
    public String getPantID() {
        return this.pantID;
    }

    //Method
    private static String generateID(){
        return "P" + pantCount;
    }

    //ToString
    @Override
    public String toString() {
        if(this.getTotalPrice() == 0){
            //product.Product
            return String.format("%25s",this.pantID) + String.format("%8s","")  + super.toString();
        } else{
            //cart.Cart
            return String.format("%9s",this.pantID) + super.toString();
        }
    }

    @Override
    public Pant clone() {
        Pant clone = (Pant) super.clone();
        // TODO: copy mutable state here, so the clone can't change the internals of the original
        return clone;
    }
}

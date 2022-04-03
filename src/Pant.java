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
    public static String generateID(){
        return "P" + pantCount;
    }

    //ToString
    @Override
    public String toString() {
        return "\t" + this.pantID + "\t\t" + super.toString();
    }

    @Override
    public Pant clone() {
        Pant clone = (Pant) super.clone();
        // TODO: copy mutable state here, so the clone can't change the internals of the original
        return clone;
    }
}

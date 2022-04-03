public class Shoe extends Product implements Cloneable{
    private String shoeID;
    private static int shoeCount = 1;

    public Shoe(){

    }

    public Shoe(String name, double price, int qty) {
        super(name, price, qty);
        this.shoeID = generateID();
        shoeCount++;

    }

    public Shoe(String name, double price, int qty, int rating) {
        super(name, price, qty, rating);
        this.shoeID = generateID();
        shoeCount++;
    }

    //Setter and getter
    public String getShoeID() {
        return shoeID;
    }

    //Method
    public static String generateID(){
        return "S" + shoeCount;
    }

    @Override
    public String toString() {
        return "\t" + this.shoeID + "\t\t" + super.toString();
    }


    @Override
    public Shoe clone() {
        Shoe clone = (Shoe) super.clone();
        // TODO: copy mutable state here, so the clone can't change the internals of the original
        return clone;
    }
}

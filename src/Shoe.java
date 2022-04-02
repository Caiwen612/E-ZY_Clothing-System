public class Shoe extends Product{
    private String shoeID;
    private static int shoeCount = 1;

    public Shoe(){

    }

    public Shoe(String name, double price, int qty) {
        super(name, price, qty);
        this.shoeID = generateID();

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


}

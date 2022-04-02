public class Pant extends Product{
    private String pantID;
    private static int pantCount = 1;

    public Pant(){

    }

    public Pant(String name, double price, int qty) {
        super(name, price, qty);
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
}

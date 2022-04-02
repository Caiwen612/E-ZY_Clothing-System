public class Accessories extends Product{
    private String accessoriesID;
    private static int accessoriesCount = 1;

    public Accessories(){

    }

    public Accessories(String name, double price, int qty) {
        super(name, price, qty);
        this.accessoriesID = generateID();
        accessoriesCount++;
    }

    //Setter and getter
    public String getAccessoriesID() {
        return accessoriesID;
    }

    //Method
    public static String generateID(){
        return "A" + accessoriesCount;
    }

    //ToString
    @Override
    public String toString() {
        return "\t" + this.accessoriesID + "\t\t" + super.toString();
    }
}

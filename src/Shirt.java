public class Shirt extends Product{
    private String shirtID;
    private static int shirtCount = 1;

    public Shirt(){
        this("",0,0);
    }

    public Shirt(String name, double price, int qty) {
        super(name, price, qty);
        this.shirtID = generateID();
        shirtCount++;
    }

    //Setter and getter
    public String getShirtID() {
        return this.shirtID;
    }

    //Method
    public static String generateID(){
        return "T" + shirtCount;
    }

    //ToString
    @Override
    public String toString() {
        return "\t" + this.shirtID + "\t\t" + super.toString();
    }

    //ToEqual
    @Override
    public boolean equals(Object object){
        if(object == null){
            return false;
        }
        if(this.getClass() != object.getClass()){
            return false;
        }
        return true;
    }
}

package product;

public class Accessories extends Product implements Cloneable{
    private String accessoriesID;
    private static int accessoriesCount = 1;

    //No-argh constructor (Best Practice)
    public Accessories(){
        this("",0,0,0);
    }

    //Parameterized constructor
    public Accessories(String name, double price, int qty, int rating) {
        super(name, price, qty, rating);
        this.accessoriesID = generateID();
        accessoriesCount++;
    }

    //Getter
    public String getAccessoriesID() {
        return accessoriesID;
    }

    //Method to generate ID
    private static String generateID(){
        return "A" + accessoriesCount;
    }

    //ToString
    @Override
    public String toString() {
        if(this.getTotalPrice() == 0){
            //Product
            return String.format("%25s",this.accessoriesID) + String.format("%8s","")  + super.toString();
        } else{
            //Cart
            return String.format("%9s",this.accessoriesID) + super.toString();
        }

    }

    //Clone
    @Override
    public Accessories clone() {
        return (Accessories) super.clone();
    }
}

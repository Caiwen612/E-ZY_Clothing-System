package product;

public class Accessories extends Product implements Cloneable{
    private String accessoriesID;
    private static int accessoriesCount = 1;

    public Accessories(){

    }

    public Accessories(String name, double price, int qty) {
        super(name, price, qty);
        this.accessoriesID = generateID();
        accessoriesCount++;
    }

    public Accessories(String name, double price, int qty, int rating) {
        super(name, price, qty, rating);
        this.accessoriesID = generateID();
        accessoriesCount++;
    }

    //Setter and getter
    public String getAccessoriesID() {
        return accessoriesID;
    }

    //Method
    private static String generateID(){
        return "A" + accessoriesCount;
    }

    //ToString
    @Override
    public String toString() {
        if(this.getTotalPrice() == 0){
            //product.Product
            return String.format("%25s",this.accessoriesID) + String.format("%8s","")  + super.toString();
        } else{
            //cart.Cart
            return String.format("%9s",this.accessoriesID) + super.toString();
        }

    }

    @Override
    public Accessories clone() {
        Accessories clone = (Accessories) super.clone();
        // TODO: copy mutable state here, so the clone can't change the internals of the original
        return clone;
    }
}
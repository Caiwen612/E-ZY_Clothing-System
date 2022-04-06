public class Shirt extends Product implements Cloneable {
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

    public Shirt(String name, double price, int qty, int rating) {
        super(name, price, qty, rating);
        this.shirtID = generateID();
        shirtCount++;
    }

    //Setter and getter
    public String getShirtID() {
        return this.shirtID;
    }
    //Method
    private static String generateID(){
        return "T" + shirtCount;
    }

    //ToString
    @Override
    public String toString() {
        if(this.getTotalPrice() == 0){
            //Product
            return String.format("%25s",this.shirtID) + String.format("%8s","")  + super.toString();
        } else{
            //Cart
            return String.format("%9s",this.shirtID) + super.toString();
        }
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

    @Override
    public Shirt clone() {
        Shirt clone = (Shirt) super.clone();
        // TODO: copy mutable state here, so the clone can't change the internals of the original
        return clone;
    }
}

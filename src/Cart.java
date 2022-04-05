import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> cartItem;
    private double totalPrice;
    private int itemCount;


    public Cart(){
        this.cartItem = new ArrayList<Product>();
        this.totalPrice = 0;
    }

    //Getter and Setter
    public List<Product> getCartItem() {
        return cartItem;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    //Method
    public Product getProduct(int index){
        return cartItem.get(index-1);
    }

    public void displayItem(){
        if(cartItem.size() != 0){
            for(int i=0; i<cartItem.size(); i++){
                System.out.println((i+1) + cartItem.get(i).toString());
            }
        } else {
            System.out.println("Cart is empty");
        }
        System.out.println("Item count: " + itemCount);
        System.out.println("Total price: " + totalPrice);
    }

    public void addItem(Product product, int quantity){
        //Check similar
        if (checkSimilar(product)){
            System.out.println("Product already exist in cart");
        } else{
            product.setQty(quantity);
            product.setTotalPrice(product.getPrice() * quantity);
            this.cartItem.add(product);
            itemCount++;
            calculateTotalPrice();
            displayItem();
            System.out.println("Product with " + quantity + " added to cart");
        }
    }

    private boolean checkSimilar(Product product) {
        boolean isExist = false;
        for (Product cartItem : cartItem) {
            if (cartItem.getName().equals(product.getName())) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }

    public void editItem(int index,int quantity){
        this.cartItem.get(index-1).setQty(quantity);
        System.out.println("Quantity of product has successfully updated.");
    }

    public void removeItem(int index){
        this.cartItem.remove(index-1);
        System.out.println("Product removed from cart");
    }

    public double calculateTotalPrice(){
        totalPrice = 0;
        for (Product product : cartItem) {
            totalPrice += product.getTotalPrice();
        }
        this.setTotalPrice(totalPrice);
        return totalPrice;
    }

    public void sortByNameAscending(){
        cartItem.sort(new sortByNameAsc());
    }

    public void sortByNameDescending(){
        cartItem.sort(new sortByNameDesc());
    }

    public void sortByTotalPriceAscending(){
        cartItem.sort(new sortByTotalPriceAsc());
    }

    public void sortByTotalPriceDescending(){
        cartItem.sort(new sortByTotalPriceDesc());
    }

    public void sortByQtyAscending(){
        cartItem.sort(new sortByQtyAsc());
    }

    public void sortByQtyDescending(){
        cartItem.sort(new sortByQtyDesc());
    }

    public void sortByQtyPriceAscending(){
        cartItem.sort(new sortByQtyPriceAsc());
    }

    public void sortByQtyPriceDescending(){
        cartItem.sort(new sortByQtyPriceDesc());
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartItem=" + cartItem +
                '}';
    }

}

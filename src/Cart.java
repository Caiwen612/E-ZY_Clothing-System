import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> cartItem;

    public Cart(){
        this.cartItem = new ArrayList<Product>();
    }

    //Getter and Setter
    public List<Product> getCartItem() {
        return cartItem;
    }

    //Method
    public void displayItem(){
        if(cartItem.size() != 0){
            for(Product product: cartItem){
                System.out.println(product);
            }
        } else {
            System.out.println("Cart is empty");
        }

    }
    public void addItem(Product product, int quantity){
        product.setQty(quantity);
        this.cartItem.add(product);
        displayItem();
        System.out.println("Product with " + quantity + " added to cart");
    }



    public void editItem(int index){

    }

    public void deleteItem(){

    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartItem=" + cartItem +
                '}';
    }
}

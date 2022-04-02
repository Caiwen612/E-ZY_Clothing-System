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
    public void addItem(){

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

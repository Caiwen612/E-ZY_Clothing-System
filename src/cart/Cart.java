package cart;

import product.Product;
import product.sort.*;
import utility.Font;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> cartItem;
    private double totalPrice;
    private int itemCount;

    DecimalFormat df2 = new DecimalFormat("0.00");

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

    public double getTotalPrice() {
        return this.totalPrice;
    }

    //Method
    public Product getProduct(int index){
        return cartItem.get(index-1);
    }

    public void displayItem(){
        if(cartItem.size() != 0){
            for(int i=0; i<cartItem.size(); i++){
                System.out.printf("%9s",(i+1));
                System.out.print(cartItem.get(i).toString());
                System.out.println();
            }
        } else {
            System.out.println("cart.Cart is empty");
        }
        System.out.println(Font.TEXT_PURPLE);
        System.out.print("Total Item : " + itemCount);
        System.out.printf("%64s"," ");
        System.out.printf("%-20s","Total price: RM" + df2.format(totalPrice));
        System.out.println(Font.RESET);
    }

    public void addItem(Product product, int quantity){
        //Check similar
        if (checkSimilar(product)){
            System.out.println("product.Product already exist in cart");
        } else{
            product.setQty(quantity);
            product.setTotalPrice(product.getPrice() * quantity);
            this.cartItem.add(product);
            itemCount++;
            calculateTotalPrice();
            displayItem();
            System.out.println("product.Product with " + quantity + " added to cart");
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
        System.out.print(Font.TEXT_YELLOW);
        System.out.printf("%n%68s","Quantity of product has successfully updated.");
    }

    public void removeItem(int index){
        this.cartItem.remove(index-1);
        System.out.print(Font.TEXT_YELLOW);
        System.out.printf("%n%58s","product.Product removed from cart");
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
        return "cart.Cart{" +
                "cartItem=" + cartItem +
                '}';
    }

}

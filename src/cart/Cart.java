package cart;

import product.Product;
import product.sort.*;
import utility.Font;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Cart implements Cloneable, Serializable {
    private ArrayList<Product> cartItem;
    private double totalPrice;
    private int itemCount;

    DecimalFormat df2 = new DecimalFormat("0.00");

    public Cart(){
        this.cartItem = new ArrayList<Product>();
        this.totalPrice = 0;
    }

    //Getter and Setter
    public ArrayList<Product> getCartItem() {
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
            System.out.println("Cart is empty");
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

    public int getItemCount() {
        return itemCount;
    }

    public void editItem(int index, int quantity){
        this.cartItem.get(index-1).setQty(quantity);
        System.out.print(Font.TEXT_YELLOW);
        System.out.printf("%n%68s","Quantity of product has successfully updated.");
    }

    public void removeItem(int index){
        this.cartItem.remove(index-1);
        System.out.print(Font.TEXT_YELLOW);
        System.out.printf("%n%58s","Product removed from cart");
    }

    public  void clearCart(){
        this.cartItem.clear();
        this.totalPrice = 0;
        this.itemCount = 0;
    }

    public void reduceStock(ArrayList<Product> productArrayList){
        //search cart and product is smae
        for (Product cart : cartItem) {
            for (Product product1 : productArrayList) {
                if (cart.getName().equals(product1.getName())) {
                    product1.minusQty(cart.getQty());
                }
            }
        }
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
        cartItem.sort(new SortByNameAsc());
    }

    public void sortByNameDescending(){
        cartItem.sort(new SortByNameDesc());
    }

    public void sortByTotalPriceAscending(){
        cartItem.sort(new SortByTotalPriceAsc());
    }

    public void sortByTotalPriceDescending(){
        cartItem.sort(new SortByTotalPriceDesc());
    }

    public void sortByQtyAscending(){
        cartItem.sort(new SortByQtyAsc());
    }

    public void sortByQtyDescending(){
        cartItem.sort(new SortByQtyDesc());
    }

    public void sortByQtyPriceAscending(){
        cartItem.sort(new SortByQtyPriceAsc());
    }

    public void sortByQtyPriceDescending(){
        cartItem.sort(new SortByQtyPriceDesc());
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartItem=" + cartItem +
                '}';
    }

    @Override
    public Cart clone() {
        try {
            Cart clone = (Cart) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

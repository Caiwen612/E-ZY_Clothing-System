package cart;

import product.Product;
import product.sort.*;
import utility.Font;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

//Author: Tay Chai Boon

public class Cart implements Cloneable, Serializable {
    //Declare data members
    private ArrayList<Product> cartItem;//Item that add by user
    private double totalPrice;//Total price of all items
    private int itemCount;//Total number of items

    DecimalFormat df2 = new DecimalFormat("0.00");

    //No-argh Constructor
    public Cart(){
        this.cartItem = new ArrayList<Product>();
        this.totalPrice = 0;
    }

    //Getter and Setter
    public ArrayList<Product> getCartItem() {
        return this.cartItem;
    }

    public double getTotalPrice() {
        calculateTotalPrice();
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getItemCount() {
        return this.itemCount;
    }


    //Method

    //Get the product
    public Product getProduct(int index){
        return cartItem.get(index-1);
    }

    //Display cart, total Item and total Price
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

    //Add item to cart
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

    //If product is similar, return true , to prevent customer order same product
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

    //Edit quantity of item that order by user
    public void editItem(int index, int quantity){
        this.cartItem.get(index-1).setQty(quantity);
        System.out.print(Font.TEXT_YELLOW);
        System.out.printf("%n%68s","Quantity of product has successfully updated.");
    }

    //Remove item from cart
    public void removeItem(int index){
        this.cartItem.remove(index-1);
        System.out.print(Font.TEXT_YELLOW);
        System.out.printf("%n%58s","Product removed from cart");
    }

    //Clear cart and reduce stock after user make a payment
    public  void clearCart(){
        this.cartItem.clear();
        this.totalPrice = 0;
        this.itemCount = 0;
    }

    //Check cart item and product to reduce the stock
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

    //Calculate total price
    public void calculateTotalPrice(){
        totalPrice = 0;
        for (Product product : cartItem) {
            totalPrice += product.getTotalPrice();
        }
        this.setTotalPrice(totalPrice);
    }

    //Sort Method by using Comparator
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

    //To String
    @Override
    public String toString() {
        return "Cart{" +
                "cartItem=" + cartItem +
                '}';
    }

    //Clone
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

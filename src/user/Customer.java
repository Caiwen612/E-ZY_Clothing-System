package user;

import cart.Cart;
import order.Order;
import utility.Font;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Customer extends People implements Cloneable, Serializable {
    private String customerID;
    private String address;
    private Cart cart;
    private ArrayList<Order> orderHistory;
    private static int custCount = 0;

    public static DecimalFormat df2 = new DecimalFormat("0.00");

    public Customer(){
        this("", "", "", "", "");
    }
    public Customer(String address) {
        this("", "", "", "", address);
    }

    public Customer(String name, String email, String password, String phoneNo, String address) {
        super(name, email, password, phoneNo);
        this.address = address;
        this.cart = new Cart();
        this.orderHistory = new ArrayList<Order>();
        custCount++;
    }

    // getter and setter
    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public static int getCustCount() {
        return custCount;
    }

    public static void setCustCount(int custCount) {
        Customer.custCount = custCount;
    }

    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }

    //add order to order history
    public void addOrder(Order order) {
        orderHistory.add(order);
    }

    public void displayOrder(){
        int orderIndex = 0;
        System.out.print(Font.TEXT_BLUE);
        System.out.println(" +=====================================================================================================+   ");
        System.out.print(" |");
        System.out.printf("%12s", "Index No |");
        System.out.printf("%7s", " No. of items  |");
        System.out.printf("%21s", " Amount paid        |");
        System.out.printf("%14s", " Subtotal    |");
        System.out.printf("%8s", "     Order date                       |");
        System.out.println("\n +=====================================================================================================+   ");
        System.out.print(Font.RESET);
        for (Order order: orderHistory){
            System.out.print("\t   " + (orderIndex +1));
            System.out.print("\t\t\t");
            System.out.print(order.getItemCount());
            System.out.print("\t\t   ");
            System.out.print("RM" + df2.format(order.getPaymentMethod().getPayAmount()));
            System.out.print("\t\t   ");
            System.out.print("     RM" + df2.format(order.getTotalPrice()));
            System.out.print("\t      ");
            System.out.print(order.getOrderDate());
            System.out.println();
            orderIndex++;
            System.out.println();
        }
    }

    public void viewOrder(int index){
        System.out.println(orderHistory.get(index-1).toString());
    }



    // methods
    public void generateCustID() {
        setCustomerID("C" + custCount);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID='" + customerID + '\'' +
                ", address='" + address + '\'' +
                ", cart=" + cart +
                ", orderHistory=" + orderHistory +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Customer customer = (Customer) o;

        if (customerID != null ? !customerID.equals(customer.customerID) : customer.customerID != null) return false;
        if (address != null ? !address.equals(customer.address) : customer.address != null) return false;
        if (cart != null ? !cart.equals(customer.cart) : customer.cart != null) return false;
        return orderHistory != null ? orderHistory.equals(customer.orderHistory) : customer.orderHistory == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (customerID != null ? customerID.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (cart != null ? cart.hashCode() : 0);
        result = 31 * result + (orderHistory != null ? orderHistory.hashCode() : 0);
        return result;
    }

    @Override
    public Customer clone() {
        Customer clone = (Customer) super.clone();
        // TODO: copy mutable state here, so the clone can't change the internals of the original
        return clone;
    }
}

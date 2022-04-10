package user;

import cart.Cart;
import order.Order;

import java.util.ArrayList;
import java.util.Objects;

public class Customer extends People implements Cloneable{
    private String customerID;
    private String address;
    private Cart cart;
    private ArrayList<Order> orderHistory;
    private static int custCount = 0;

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
        System.out.println("Order Index\tTotal order of item\tOrder Date\t Total amount of item");
        for (Order order: orderHistory){
            System.out.println((orderIndex +1) + order.getItemCount() + order.getOrderDate()  + order.getTotalPrice());
            orderIndex++;

        }
    }

    public void viewOrder(int index){
        System.out.println(orderHistory.get(index-1).toString());
    }

    public void removeOrder(int index){
        orderHistory.remove(index-1);
    }

    // methods
    public void generateCustID() {
        setCustomerID("C" + custCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerID, customer.customerID) && Objects.equals(address, customer.address) && Objects.equals(cart, customer.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customerID, address, cart);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID='" + customerID + '\'' +
                ", address='" + address + '\'' +
                ", cart=" + cart +
                '}';
    }

    @Override
    public Customer clone() {
        Customer clone = (Customer) super.clone();
        // TODO: copy mutable state here, so the clone can't change the internals of the original
        return clone;
    }
}

import java.util.Objects;

public class Customer extends People{
    private String customerID;
    private String address;
    private Cart cart;
    private Order orderHistory;
    private static int custCount = 0;

    public Customer(String address, Cart cart, Order orderHistory) {
        this("", "", "", "", "", new Cart(), new Order());
        custCount++;
    }

    public Customer(String name, String email, String password, String phoneNo, String address, Cart cart, Order orderHistory) {
        super(name, email, password, phoneNo);
        this.address = address;
        this.cart = cart;
        this.orderHistory = orderHistory;
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
}

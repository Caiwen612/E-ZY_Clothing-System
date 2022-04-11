package order;

import cart.Cart;
import payment.Bank;
import payment.DebitCredit;
import payment.EWallet;
import payment.Payment;
import product.Product;
import user.Customer;
import utility.Font;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

public class Order{
    private Customer customer;
    private Cart orderDetails;
    private Payment paymentMethod;
    private List<Product> orderList;
    private Date orderDate;

    DecimalFormat df2 = new DecimalFormat("0.00");
    public Order(){
        this(null, null, null);
    }

    public Order(Customer customer, Cart orderDetails, Payment paymentMethod) {
        this.customer = customer;
        this.orderDetails = orderDetails;
        this.paymentMethod = paymentMethod;

    }
    //Getter and setter
    public Customer getCustomer() {
        return customer;
    }

    public Cart getOrderDetails() {
        return orderDetails;
    }

    public Payment getPaymentMethod() {
        return paymentMethod;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public List<Product> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Product> orderList) {
        this.orderList = orderList;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOrderDetails(Cart orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void setPaymentMethod(Payment paymentMethod) {
        this.paymentMethod = paymentMethod;
        this.orderDate = paymentMethod.getPaymentDate();
    }

    public double getTotalPrice() {
        return this.getOrderDetails().getTotalPrice();
    }

    public int getItemCount() {
        return this.getOrderDetails().getItemCount();
    }


    @Override
    public String toString() {
        String output = "";
        System.out.print("<<< CUSTOMER DETAILS >>>" );
        System.out.print("\n\tID: " + this.getCustomer().getCustomerID());
        System.out.print("\n\tCustomer Name: " + this.getCustomer().getName());
        System.out.print("\n\tEmail: " + this.getCustomer().getEmail());
        System.out.print("\n\tPhone Number: " + this.getCustomer().getPhoneNo());
        System.out.print("\n\tAddress: " + this.getCustomer().getAddress());
        System.out.print("\n\tPhone: " + this.getCustomer().getPhoneNo());
        System.out.print( "\n------------------------------------------------------");
        System.out.print("\n<<< ORDER DETAILS >>>");
        System.out.println("\n\tOrder Date: " + this.getOrderDate());
        System.out.println("");
        System.out.println("Note: layout is according this sequence {Index,Product ID,Product Name, Price, Quantity, rating, subtotal}");
        if(orderList.size() != 0){
            for(int i=0; i< orderList.size(); i++){
                System.out.printf("%9s",(i+1));
                System.out.print(orderList.get(i).toString());
                System.out.println();
            }
        } else {
            System.out.println("Cart is empty");
        }
        System.out.println(Font.TEXT_PURPLE);
        System.out.print("Total Item : " + this.getOrderDetails().getItemCount());
        System.out.printf("%64s"," ");
        System.out.printf("%-20s","Total price: RM" + df2.format(this.getOrderDetails().getTotalPrice()));
        System.out.println(Font.RESET);
        System.out.print("\n------------------------------------------------------");
        System.out.println("\n<<< PAYMENT DETAILS >>>");
        if(this.getPaymentMethod() instanceof Bank) {
            System.out.println(((Bank) this.getPaymentMethod()).toString());
        }
        else if(this.getPaymentMethod() instanceof DebitCredit) {
            System.out.println(((DebitCredit)this.getPaymentMethod()).toString());
        } else if(this.getPaymentMethod() instanceof EWallet) {
            System.out.println(((EWallet)this.getPaymentMethod()).toString());
        }
        System.out.print("\n------------------------------------------------------");
        return output;
    }
}



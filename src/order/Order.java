package order;

import cart.Cart;
import payment.Bank;
import payment.DebitCredit;
import payment.EWallet;
import payment.Payment;
import product.Product;
import user.Customer;
import utility.Font;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
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
        this.orderList = new ArrayList<>();
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

    public double getTotalPrice() {
        return this.getOrderDetails().getTotalPrice();
    }

    public int getItemCount() {
        return this.getOrderDetails().getItemCount();
    }

    public void setOrderList(List<Product> orderList) {
        this.orderList = orderList;
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
        System.out.println(" +=================================================================================================+   ");
        System.out.print(" |");
        System.out.printf("%12s", "Index No |");
        System.out.printf("%7s", "ID  |");
        System.out.printf("%21s", "Name        |");
        System.out.printf("%14s", "Price    |");
        System.out.printf("%8s", "Qty  |");
        System.out.printf("%13s", "Rating   |");
        System.out.printf("%24s", "Total per Item    |\n");
        System.out.print(" +=================================================================================================+   ");
        System.out.println();
        System.out.print(Font.RESET);
        if(orderList.size() != 0){
            for(int i=0; i< orderList.size(); i++){
                System.out.printf("%9s",(i+1));
                System.out.print(orderList.get(i).toString());
                System.out.println();
            }
        } else {
            System.out.println("There are no orders");
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



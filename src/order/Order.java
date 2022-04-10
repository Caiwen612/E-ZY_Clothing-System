package order;

import cart.Cart;
import payment.Payment;
import user.Customer;

import java.util.Date;

public class Order{
    private Customer customer;
    private Cart orderDetails;
    private Payment paymentMethod;
    private Date orderDate;

    public Order(){
        this(new Customer(), new Cart(), new Payment());
    }

    public Order(Customer customer, Cart orderDetails, Payment paymentMethod) {
        this.customer = customer;
        this.orderDetails = orderDetails;
        this.paymentMethod = paymentMethod;
        this.orderDate = paymentMethod.getPaymentDate();
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




//    public static void removeOrder(int index){
//        paymentList.remove(index-1);
//        System.out.print("order.Order removed");
//    }
//
//    public static void displayOrder(){
//        if(paymentList.size() != 0){
//            for(int i=0; i<paymentList.size(); i++){
//                System.out.print(i+1);
//                System.out.print(paymentList.get(i));
//                System.out.println();
//            }
//        } else {
//            System.out.println("There are no orders");
//        }
//    }
//
    @Override
    public String toString() {
        return "\t" +  + "\t" + orderDate;
    }
}



package order;

import payment.Payment;

import java.util.ArrayList;
import java.util.List;

public class Order{

    public static List<Payment> paymentList;
    private static int paymentCount;

    public Order(){
        paymentList = new ArrayList<Payment>();
    }

    public void addPayment(Payment payment){
        paymentList.add(payment);
        paymentCount++;
    }

    //remove payment
    public void removePayment(Payment payment){
        paymentList.remove(payment);
    }

    public List<Payment> getPaymentList(){
        return paymentList;
    }

    public static void removeOrder(int index){
        paymentList.remove(index-1);
        System.out.print("order.Order removed");
    }

    public static void displayOrder(){
        if(paymentList.size() != 0){
            for(int i=0; i<paymentList.size(); i++){
                System.out.print(i+1);
                System.out.print(paymentList.get(i));
                System.out.println();
            }
        } else {
            System.out.println("There are no orders");
        }
    }

    @Override
    public String toString() {
        String output = "";
        for(Payment payment: paymentList){
            output += paymentCount + "\t" + payment.getPaymentID() + "\t\t" + payment.getPaymentDate();
        }
        return output;
    }
}



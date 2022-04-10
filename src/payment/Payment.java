package payment;

import java.util.Date;

public class Payment {
    private  String paymentID;
    private Date paymentDate;
    private static int paymentCount = 1;
    private double totalPrice;

    public Payment(){
        this.paymentID = generatePayID();
        this.paymentDate = new Date();
        paymentCount++;
    }

    //Getter & Setter
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    //Method
    public static String generatePayID(){
        return "PM" + paymentCount;
    }


    //ToString
    @Override
    public String toString() {
        return "\n  Payment ID: " + paymentID +
                "\nPayment Date: " + paymentDate +
                "\n\nThank you for shopping!";
    }
}


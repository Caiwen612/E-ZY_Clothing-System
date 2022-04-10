package payment;

import utility.Font;

public class DebitCredit extends Payment implements Cloneable{

    private String bankName;
    private int cardNo;
    private String validDate;
    public double payAmount;

    public DebitCredit(){
        this("",0,"",0);
    }
    public DebitCredit(String bankName, int cardNo, String validDate, double payAmount){
        this.bankName = bankName;
        this.cardNo = cardNo;
        this.validDate = validDate;
        this.payAmount = payAmount;
    }

    //setter
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }
    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }
    public void setPayAmount(double payAmount) {
        this.payAmount = payAmount;
    }


    @Override
    public String toString() {
        System.out.print(Font.TEXT_CYAN);
        System.out.printf("%20s", "+-------------+");
        System.out.printf("%n%20s", "|   Receipt   |");
        System.out.printf("%n%20s", "+-------------+");
        System.out.println(Font.RESET);
        return " Total Price: RM" + getTotalPrice() +
                "\n Amount paid: RM" + payAmount +
                "\n      Change: RM" + (payAmount - getTotalPrice()) +
                "\n   Bank Name: " + bankName +
                "\n Card Number: " + cardNo +
                super.toString();
    }

    @Override
    public DebitCredit clone() {
        DebitCredit clone = (DebitCredit) super.clone();
        // TODO: copy mutable state here, so the clone can't change the internals of the original
        return clone;
    }
}


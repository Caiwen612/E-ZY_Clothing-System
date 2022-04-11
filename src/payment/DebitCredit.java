package payment;

import utility.Font;

import java.text.DecimalFormat;

public class DebitCredit extends Payment implements Cloneable{

    private String bankName;
    private int cardNo;
    private String validDate;

    public static DecimalFormat df2 = new DecimalFormat("0.00");

    public DebitCredit(){
        this("",0,"");
    }
    public DebitCredit(String bankName, int cardNo, String validDate){
        this.bankName = bankName;
        this.cardNo = cardNo;
        this.validDate = validDate;
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



    @Override
    public String toString() {
        System.out.print(Font.TEXT_CYAN);
        System.out.printf("%20s", "+-------------+");
        System.out.printf("%n%20s", "|   Receipt   |");
        System.out.printf("%n%20s", "+-------------+");
        System.out.println(Font.RESET);
        return " Total Price: RM" + df2.format(getTotalPrice()) +
                "\n Amount paid: RM" + df2.format(this.getPayAmount()) +
                "\n      Change: RM" + df2.format(this.getPayAmount() - getTotalPrice()) +
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


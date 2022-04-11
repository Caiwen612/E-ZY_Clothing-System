package payment;

import utility.Font;

import java.text.DecimalFormat;

public class Bank extends Payment implements Cloneable {

    private String bankName;
    private int bankNo;

    public static DecimalFormat df2 = new DecimalFormat("0.00");

    public Bank(){
        this("",0);
    }
    public Bank(String bankName, int bankNo){
        super();
        this.bankName = bankName;
        this.bankNo = bankNo;
    }

    //setter
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public void setBankNo(int bankNo) {
        this.bankNo = bankNo;
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
                "\n Bank Number: " + bankNo +
                super.toString();
    }

    @Override
    public Bank clone() {
        Bank clone = (Bank) super.clone();
        // TODO: copy mutable state here, so the clone can't change the internals of the original
        return clone;
    }
}

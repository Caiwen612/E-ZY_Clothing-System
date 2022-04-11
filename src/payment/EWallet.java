package payment;

import utility.Font;

import java.text.DecimalFormat;

public class EWallet extends Payment implements Cloneable {
    // Data members
    private int accountID;
    private String userName;

    public static DecimalFormat df2 = new DecimalFormat("0.00");

    // No-argh constructor
    public EWallet(){
        this(0,"");
    }
    
    // Parameterized constructor
    public EWallet(int accountID, String userName){
        super();
        this.accountID = accountID;
        this.userName = userName;
    }

    //setter
    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    // ToString
    @Override
    public String toString() {
        System.out.print(Font.TEXT_CYAN);
        System.out.printf("%20s", "+-------------+");
        System.out.printf("%n%20s", "|   Receipt   |");
        System.out.printf("%n%20s", "+-------------+");
        System.out.println(Font.RESET);
        return " Total Price: RM" + df2.format(getTotalPrice())  +
                "\n Amount paid: RM" + df2.format(this.getPayAmount())  +
                "\n      Change: RM" + df2.format(this.getPayAmount() - getTotalPrice()) +
                "\n  Account ID: " + accountID +
                "\n    Username: " + userName +
                super.toString();
    }

    // Clone
    @Override
    public EWallet clone() {
        EWallet clone = (EWallet) super.clone();
        // TODO: copy mutable state here, so the clone can't change the internals of the original
        return clone;
    }
}

import utility.Font;

public class EWallet extends Payment {

    private int accountID;
    private String userName;
    public double payAmount;

    public EWallet(){
        this(0,"",0);
    }
    public EWallet(int accountID, String userName, double payAmount){
        super();
        this.accountID = accountID;
        this.userName = userName;
        this.payAmount = payAmount;
    }

    //setter
    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setBalance(double payAmount) {
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
                "\n  Account ID: " + accountID +
                "\n    Username: " + userName +
                super.toString() + "\n\nThank you for shopping!";
    }
}

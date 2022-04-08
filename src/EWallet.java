public class EWallet extends Payment {

    private int accountID;
    private String userName;
    private double payAmount;

    public EWallet(){
        this(0,"",0);
    }
    public EWallet(int accountID, String userName, double payAmount){
        super();
        this.accountID = accountID;
        this.userName = userName;
        this.payAmount = payAmount;
    }

    //getter
    public int getAccountID() {
        return accountID;
    }
    public String getUserName() {
        return userName;
    }
    public double getPayAmount() {return payAmount;}

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
        System.out.println("+-------------+");
        System.out.println("|   Receipt   |");
        System.out.println("+-------------+");
        return "Total Price: RM" + getTotalPrice() +
                "\nAmount paid: RM" + payAmount +
                "\nChange: RM" + (payAmount - getTotalPrice()) +
                "\nAccount ID: " + accountID +
                "\nUsername: " + userName +
                super.toString() + "\nThank you for shopping!";
    }
}

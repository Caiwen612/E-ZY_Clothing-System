import utility.Font;

public class Bank extends Payment{

    private String bankName;
    private int bankNo;
    public double payAmount;

    public Bank(){
        this("",0,0);
    }
    public Bank(String bankName, int bankNo, double payAmount){
        super();
        this.bankName = bankName;
        this.bankNo = bankNo;
        this.payAmount = payAmount;
    }

    //setter
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public void setBankNo(int bankNo) {
        this.bankNo = bankNo;
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
                "\n   Bank Name: " + bankName +
                "\n Bank Number: " + bankNo +
                super.toString() + "\n\nThank you for shopping!";
    }
}

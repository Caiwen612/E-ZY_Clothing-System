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
        System.out.println("+-------------+");
        System.out.println("|   Receipt   |");
        System.out.println("+-------------+");
        return "Total Price: RM" + getTotalPrice() +
                "\nAmount paid: RM" + payAmount +
                "\nChange: RM" + (payAmount - getTotalPrice()) +
                "\nBank Name: " + bankName +
                "\nBank Number: " + bankNo +
                super.toString() + "\nThank you for shopping!";

    }

}

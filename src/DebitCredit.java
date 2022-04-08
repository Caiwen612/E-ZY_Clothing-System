public class DebitCredit extends Payment{

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
                "\nCard Number: " + cardNo +
                super.toString() + "\nThank you for shopping!";
    }
}


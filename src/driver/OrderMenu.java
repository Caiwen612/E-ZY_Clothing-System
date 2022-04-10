package driver;

import order.Order;
import payment.Bank;
import payment.DebitCredit;
import payment.EWallet;
import payment.Payment;
import utility.Font;
import utility.Validation;
import utility.ValidationException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class OrderMenu {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        EWallet eWallet = new EWallet();
        DebitCredit debitCredit = new DebitCredit();

        orderMenu(bank, eWallet, debitCredit);
    }

    public static void orderMenu(Payment bank, Payment eWallet, Payment debitCredit) throws InterruptedException {
        System.out.println("+------------+");
        System.out.println("|   Orders   |");
        System.out.println("+------------+");

        Order order = new Order();
        if (bank.payAmount != 0) {
            order.addPayment(bank);
        } else if (eWallet.payAmount != 0) {
            order.addPayment(eWallet);
        } else if (debitCredit.payAmount != 0) {
            order.addPayment(debitCredit);
        }

        Order.displayOrder();

        System.out.println("\n[1] Remove order");
        System.out.println("[2] Return to main menu");

        boolean orderOptionError = true;
        int orderOption = 0;
        do {
            try {
                System.out.print("Enter your option: ");
                orderOption = input.nextInt();
                Validation.validOption(orderOption, 1, 2);
                orderOptionError = false;
            } catch (ValidationException e) {
                System.err.println(e.getMessage());
                Thread.sleep(1000);
            } catch (InputMismatchException e) {
                System.err.println(Font.useFont(Font.BOLD_RED, "Please only key in integer"));
                input.nextLine();
                Thread.sleep(1000);
            }
        } while (orderOptionError);
        switch (orderOption){
            case 1:
                removeOrder(bank, eWallet, debitCredit);
                break;
            case 2:
                //return main menu
                break;
        }
    }


    public static void removeOrder(Payment bank, Payment eWallet, Payment debitCredit) throws InterruptedException {
        System.out.print("\n");

        boolean orderIndexError = true;
        int orderIndex = 0;
        do {
            try {
                System.out.print("Enter the number of the item that you want to remove: ");
                orderIndex = input.nextInt();
                Validation.validOption(orderIndex, 1, Order.paymentList.size());
                orderIndexError = false;
            } catch (ValidationException e) {
                System.err.println(e.getMessage());
                Thread.sleep(1000);
            } catch (InputMismatchException e) {
                System.err.println(Font.useFont(Font.BOLD_RED, "Please only key in integer"));
                input.nextLine();
                Thread.sleep(1000);
            }
        } while (orderIndexError);
        boolean removeOrderError = true;
        char removeOrder = 'A';
        do {
            try {
                System.out.print("Do you want to remove this product from your cart? (Y/N) ");
                removeOrder = Character.toUpperCase(input.next().charAt(0));
                Validation.validCharYN(removeOrder);
                removeOrderError = false;
            } catch (ValidationException e) {
                System.err.println(e.getMessage());
                input.nextLine();
                Thread.sleep(1000);
            }
        } while (removeOrderError);
        if (removeOrder == 'Y') {
            Order.removeOrder(orderIndex);
            System.out.println("\nPress enter key to go back cart.Cart menu");
            pressAnyKeyToContinue();
            orderMenu(bank, eWallet, debitCredit);

        } else{
            System.out.println("\nPress enter key to go back cart.Cart menu");
            pressAnyKeyToContinue();
            orderMenu(bank, eWallet, debitCredit);
        }
    }

    public static void pressAnyKeyToContinue(){
        input.nextLine();
        input.nextLine();
    }
}

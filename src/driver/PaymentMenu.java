package driver;

import cart.Cart;
import payment.Bank;
import payment.DebitCredit;
import payment.EWallet;
import payment.Payment;
import product.*;
import utility.Font;
import utility.Validation;
import utility.ValidationException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PaymentMenu {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Payment> payment = new ArrayList<>();
        ArrayList<Product> productArrayList = new ArrayList<>();
        Cart cart = new Cart();
        setProducts(productArrayList);
        cart.addItem(productArrayList.get(2).clone(), 2);
        cart.addItem(productArrayList.get(3).clone(), 6);
        cart.addItem(productArrayList.get(4).clone(), 4);
        cart.addItem(productArrayList.get(5).clone(), 5);
        cart.addItem(productArrayList.get(6).clone(), 2);
        cart.addItem(productArrayList.get(7).clone(), 6);
        paymentMenu(cart, payment);
    }

    public static void paymentMenu(Cart cart, ArrayList<Payment> payment) throws InterruptedException {
        clearScreen();
        System.out.print(Font.TEXT_CYAN);
        System.out.printf("%55s",  "+-------------+");
        System.out.printf("%n%55s","|   payment.Payment   |");
        System.out.printf("%n%55s","+-------------+");
        System.out.print(Font.RESET);
        System.out.println(Font.TEXT_BRIGHT_MAGENTA);
        System.out.printf("%59s","Choose A payment.Payment Method");
        System.out.print(Font.RESET);
        System.out.printf("%n%61s","[1] payment.Bank             ");
        System.out.printf("%n%61s","[2] E-Wallet         ");
        System.out.printf("%n%61s","[3] Debit/Credit     ");

        //Get input for payment method
        boolean paymentOptionError = true;
        int paymentOption = 0;
        do {
            try {
                System.out.print(Font.TEXT_BLUE);
                System.out.printf("%n%55s","Enter your option: ");
                paymentOption = input.nextInt();
                System.out.print(Font.RESET);
                System.out.printf("%26s","");
                Validation.validOption(paymentOption, 1, 3);
                paymentOptionError = false;
            } catch (ValidationException e) {
                System.err.print(e.getMessage());
                Thread.sleep(1000);
            } catch (InputMismatchException e) {
                System.out.print(Font.BOLD_RED);
                System.out.printf("%59s","Please only key in integer");
                input.nextLine();
                System.out.print(Font.RESET);
                Thread.sleep(1000);
            }
        } while (paymentOptionError);

        switch (paymentOption) {
            case 1 -> {
                Payment bank1 = new Bank();
                bank(bank1, cart);
                payment.add(bank1);
            }
            case 2 -> {
                Payment eWallet1 = new EWallet();
                eWallet(eWallet1, cart);
                payment.add(eWallet1);
            }
            case 3 -> {
                Payment debitCredit1 = new DebitCredit();
                debitCredit(debitCredit1, cart);
                payment.add(debitCredit1);
            }
        }
    }

    private static void setProducts(ArrayList<Product> productArrayList) {
        //product.Shirt
        Product p1 = new Shirt("Crew Tee", 25.30, 20, 3);
        Product p2 = new Shirt("Hoodie", 50.20, 10, 4);
        Product p3 = new Shirt("Jacket", 67.70, 30, 2);
        Product p4 = new Shirt("Poplin product.Shirt", 43.80, 40, 4);
        Product p5 = new Shirt("Dress", 40.50, 30, 5);
        Product p6 = new Shirt("Jumpsuit", 70.60, 25, 3);
        Product p7 = new Shirt("Sport wear", 80.30, 66, 5);
        //product.Pant
        Product p8 = new Pant("Jeans", 30.50, 12, 5);
        Product p9 = new Pant("Trousers", 40.20, 5, 5);
        Product p10 = new Pant("Cotton pants", 35.30, 20, 4);
        Product p11 = new Pant("Short pan", 15.60, 26, 3);
        Product p12 = new Pant("Ripped pants", 43.20, 19, 2);
        Product p13 = new Pant("Sport pan", 30.60, 45, 4);
        Product p14 = new Pant("Cropped pant", 39.20, 45, 1);
        //product.Accessories
        Product p15 = new Accessories("Cap", 14.60, 30, 2);
        Product p16 = new Accessories("Short wallet", 20.60, 25, 3);
        Product p17 = new Accessories("Long wallet", 30.60, 17, 2);
        Product p18 = new Accessories("Earring", 10.30, 30, 5);
        Product p19 = new Accessories("Headband", 15.40, 50, 4);
        Product p20 = new Accessories("Sunglasses", 12.50, 45, 3);
        //product.Shoe
        Product p21 = new Shoe("Running shoe", 60, 25, 4);
        Product p22 = new Shoe("Leather shoe", 300, 10, 5);
        Product p23 = new Shoe("Basketball shoe", 200, 25, 3);
        Product p24 = new Shoe("Badminton shoe", 180, 16, 3);
        Product p25 = new Shoe("High heel shoe", 66.80, 30, 5);
        Product p26 = new Shoe("Relax Spot shoe", 100.30, 20, 5);

        //Add all product to arraylist
        productArrayList.add(p1);
        productArrayList.add(p2);
        productArrayList.add(p3);
        productArrayList.add(p4);
        productArrayList.add(p5);
        productArrayList.add(p6);
        productArrayList.add(p7);
        productArrayList.add(p8);
        productArrayList.add(p9);
        productArrayList.add(p10);
        productArrayList.add(p11);
        productArrayList.add(p12);
        productArrayList.add(p13);
        productArrayList.add(p14);
        productArrayList.add(p15);
        productArrayList.add(p16);
        productArrayList.add(p17);
        productArrayList.add(p18);
        productArrayList.add(p19);
        productArrayList.add(p20);
        productArrayList.add(p21);
        productArrayList.add(p22);
        productArrayList.add(p23);
        productArrayList.add(p24);
        productArrayList.add(p25);
        productArrayList.add(p26);
    }

    // Receive payment.Bank details
    public static void bank(Payment bank, Cart cart) {
        clearScreen();
        bank.setTotalPrice(cart.getTotalPrice());
        System.out.println(Font.TEXT_BRIGHT_MAGENTA);
        System.out.printf("%67s","You have chose payment.Bank as payment method");
        System.out.println(Font.RESET);
        System.out.printf("%59s","Total Price (RM): " + cart.getTotalPrice());

        System.out.println(Font.TEXT_YELLOW);
        System.out.printf("%n%69s","Available banks: MAYBANK, CIMB, RHB, HSBC");
        System.out.println(Font.TEXT_BLUE);
        System.out.printf("%47s","Choose a bank: ");
        input.nextLine();
        String bankName = input.nextLine().toUpperCase();
        while (!bankName.matches("MAYBANK|CIMB|RHB|HSBC")) {
            System.out.print(Font.BOLD_RED);
            System.out.printf("%52s","Invalid!");
            System.out.println(Font.RESET);
            System.out.print(Font.TEXT_BLUE);
            System.out.printf("%47s","Enter a valid bank name: ");
            System.out.print(Font.RESET);
            bankName = input.next().toUpperCase();
        }
        ((Bank) bank).setBankName(bankName);

        boolean bankNoError = true;
        int bankNo = 0;
        do {
            try {
                System.out.print(Font.TEXT_BLUE);
                System.out.printf("%47s","Enter account ID: ");
                bankNo = input.nextInt();
                System.out.print(Font.RESET);
                bankNoError = false;
            } catch (InputMismatchException e) {
                System.out.print(Font.BOLD_RED);
                System.out.printf("%62s","Please only key in integer");
                input.nextLine();
                System.out.print(Font.RESET);
            }
        } while (bankNoError);
        ((Bank) bank).setBankNo(bankNo);

        double payAmount = 0;
        do {
            try {
                System.out.print(Font.TEXT_BLUE);
                System.out.printf("%47s","Enter payment amount (RM): ");
                payAmount = input.nextDouble();
                System.out.print(Font.RESET);
                if (payAmount < cart.getTotalPrice()) {
                    System.out.print(Font.BOLD_RED);
                    System.out.printf("%58s","Insufficient amount!\n");
                    System.out.print(Font.RESET);
                }
            } catch (InputMismatchException e) {
                System.out.print(Font.BOLD_RED);
                System.out.printf("%50s","Invalid!");
                System.out.println(Font.RESET);
                input.nextLine();
            }
        } while (payAmount < cart.getTotalPrice());
        ((Bank) bank).setBalance(payAmount);

        clearScreen();
        System.out.println(bank);
        System.out.println("Press enter key to return to main menu");
        pressAnyKeyToContinue();

        //main menu
    }

    // Receive E-Wallet details
    public static void eWallet(Payment eWallet, Cart cart) {
        clearScreen();
        eWallet.setTotalPrice(cart.getTotalPrice());
        System.out.println(Font.TEXT_BRIGHT_MAGENTA);
        System.out.printf("%70s","You have chose E-Wallet as payment method");
        System.out.println(Font.RESET);
        System.out.printf("%61s","Total Price (RM): " + cart.getTotalPrice());
        System.out.println();

        boolean accIDError = true;
        int accountID = 0;
        do {
            try {
                System.out.print(Font.TEXT_BLUE);
                System.out.printf("%n%49s","Enter account ID: ");
                accountID = input.nextInt();
                System.out.print(Font.RESET);
                accIDError = false;
            } catch (InputMismatchException e) {
                System.out.print(Font.BOLD_RED);
                System.out.printf("%62s","Please only key in integer");
                System.out.print(Font.RESET);
                input.nextLine();
            }
        } while (accIDError);
        ((EWallet) eWallet).setAccountID(accountID);

        System.out.print(Font.TEXT_BLUE);
        System.out.printf("%49s","Enter username: ");
        System.out.print(Font.RESET);
        input.nextLine();
        String userName = input.nextLine();
        while (!userName.matches("[a-zA-Z]+")) {
            System.out.print(Font.BOLD_RED);
            System.out.printf("%52s","Invalid!");
            System.out.println(Font.RESET);
            System.out.print(Font.TEXT_BLUE);
            System.out.printf("%49s","Enter valid username: ");
            userName = input.next();
            System.out.print(Font.RESET);
        }
        ((EWallet) eWallet).setUserName(userName);

        double payAmount = 0;
        do {
            try {
                System.out.print(Font.TEXT_BLUE);
                System.out.printf("%49s","Enter payment amount (RM): ");
                payAmount = input.nextDouble();
                System.out.print(Font.RESET);
                if (payAmount < cart.getTotalPrice()) {
                    System.out.print(Font.BOLD_RED);
                    System.out.printf("%59s","Insufficient amount!\n");
                    System.out.print(Font.RESET);
                }
            } catch (InputMismatchException e) {
                System.out.print(Font.BOLD_RED);
                System.out.printf("%52s","Invalid!");
                System.out.println(Font.RESET);
                input.nextLine();
            }
        } while (payAmount < cart.getTotalPrice());
        ((EWallet) eWallet).setBalance(payAmount);

        clearScreen();
        System.out.println(eWallet);
        System.out.println("Press enter key to return to main menu");
        pressAnyKeyToContinue();
        //main menu
    }

    // Receive Debit/Credit details
    public static void debitCredit(Payment debitCredit, Cart cart) {
        clearScreen();
        debitCredit.setTotalPrice(cart.getTotalPrice());
        System.out.println(Font.TEXT_BRIGHT_MAGENTA);
        System.out.printf("%70s","You have chose debit/credit as payment method");
        System.out.println(Font.RESET);
        System.out.printf("%59s","Total Price (RM): " + cart.getTotalPrice());
        System.out.println(Font.TEXT_YELLOW);
        System.out.printf("%n%69s","Available banks: MAYBANK, CIMB, RHB, HSBC");

        System.out.println(Font.TEXT_BLUE);
        System.out.printf("%47s","Choose a bank: ");
        input.nextLine();
        String bankName = input.nextLine().toUpperCase();
        while (!bankName.matches("MAYBANK|CIMB|RHB|HSBC")) {
            System.out.print(Font.BOLD_RED);
            System.out.printf("%52s","Invalid!");
            System.out.println(Font.RESET);
            System.out.print(Font.TEXT_BLUE);
            System.out.printf("%47s","Enter a valid bank name: ");
            System.out.print(Font.RESET);
            bankName = input.next().toUpperCase();
        }
        ((DebitCredit) debitCredit).setBankName(bankName);

        boolean cardNoError = true;
        int cardNo = 0;
        do {
            try {
                System.out.print(Font.TEXT_BLUE);
                System.out.printf("%47s","Enter account ID: ");
                cardNo = input.nextInt();
                System.out.print(Font.RESET);
                cardNoError = false;
            } catch (InputMismatchException e) {
                System.out.print(Font.BOLD_RED);
                System.out.printf("%62s","Please only key in integer\n");
                System.out.print(Font.RESET);
                input.nextLine();
            }
        } while (cardNoError);
        ((DebitCredit) debitCredit).setCardNo(cardNo);

        System.out.print(Font.TEXT_BLUE);
        System.out.printf("%47s","Enter Valid Date (MM-YY): ");
        input.nextLine();
        System.out.print(Font.RESET);
        String validDate = input.nextLine();
        while (!validDate.matches("\\b([1-9]|1[0-2])\\b-\\b([0-9]|[1-9][0-9])\\b")) {
            System.out.print(Font.BOLD_RED);
            System.out.printf("%52s","Invalid!");
            System.out.print(Font.RESET);
            System.out.println(Font.TEXT_BLUE);
            System.out.printf("%47s","Please enter Valid Date (MM-YY): ");
            System.out.print(Font.RESET);
            validDate = input.next();
        }
        ((DebitCredit) debitCredit).setValidDate(validDate);


        double payAmount = 0;
        do {
            try {
                System.out.print(Font.TEXT_BLUE);
                System.out.printf("%47s","Enter payment amount (RM): ");
                payAmount = input.nextDouble();
                System.out.print(Font.RESET);
                if (payAmount < cart.getTotalPrice()) {
                    System.out.print(Font.BOLD_RED);
                    System.out.printf("%59s","Insufficient amount!\n");
                    System.out.print(Font.RESET);
                }
            } catch (InputMismatchException e) {
                System.out.print(Font.BOLD_RED);
                System.out.printf("%52s","Invalid!");
                System.out.println(Font.RESET);
                input.nextLine();
            }
        } while (payAmount < cart.getTotalPrice());
        ((DebitCredit) debitCredit).setBalance(payAmount);

        clearScreen();
        System.out.println(debitCredit);
        System.out.println("Press enter key to return to main menu");
        pressAnyKeyToContinue();
        //main menu
    }

    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    public static void pressAnyKeyToContinue(){
        input.nextLine();
        input.nextLine();
    }
}

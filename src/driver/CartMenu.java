//package driver;
//
//import cart.Cart;
//import utility.Font;
//import utility.Validation;
//import utility.ValidationException;
//
//import java.util.InputMismatchException;
//import java.util.Scanner;
//
//
//
//public class CartMenu {
//    static Scanner input = new Scanner(System.in);
//    public static void main(String [] args) throws InterruptedException{
//        //Create a new cart
//        Cart cart = new Cart();
//
//
//        System.out.println("\t\t+----------+");
//        System.out.println("\t\t|   cart.Cart   |");
//        System.out.println("\t\t+----------+");
//        //Display cart
////        cart.getCartItem();
//        System.out.println("\t\t[1] Add Item");
//        System.out.println("\t\t[2] Edit Item");
//        System.out.println("\t\t[3] Delete Item");
//        System.out.println("\t\t[4] Sort Item");
//        System.out.println("\t\t[5] Make payment.Payment");
//        System.out.println("\t\t[6] Back to previous Menu");
//
//        // Get input for cart option
//        boolean cartOptionError = true;
//        int cartOption = 0;
//        do {
//            try {
//                System.out.print("Enter your option: ");
//                cartOption = input.nextInt();
//                Validation.validOption(cartOption,1,6);
//                cartOptionError = false;
//            } catch (ValidationException e) {
//                System.err.println(e.getMessage());
//                Thread.sleep(1000);
//            } catch (InputMismatchException e) {
//                System.err.println(Font.useFont(Font.BOLD_RED, "Please only key in integer"));
//                input.nextLine();
//                Thread.sleep(1000);
//            }
//        } while (cartOptionError);
//        switch (cartOption) {
//            case 1:
//
//        }
//
//
//    }
//
//}

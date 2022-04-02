import utility.Font;
import utility.Validation;
import utility.ValidationException;

import java.util.InputMismatchException;
import java.util.Scanner;



public class CartMenu {
    public static void main(String [] args) throws InterruptedException{
        Scanner input = new Scanner(System.in);
        System.out.println("\tCart");
        //CartDetails
        System.out.println("\t\t[1] Add Item");
        System.out.println("\t\t[2] Edit Item");
        System.out.println("\t\t[3] Delete Item");
        System.out.println("\t\t[4] Sort Item");
        System.out.println("\t\t[5] Make Payment");
        System.out.println("\t\t[6] Back to previous Menu");
        
        

        // Get input for cart option
        boolean cartOptionError = true;
        int cartOption = 0;
        do {
            try {
                System.out.print("Enter your option: ");
                cartOption = input.nextInt();
                Validation.validOption(cartOption,1,6);
                cartOptionError = false;
            } catch (ValidationException e) {
                System.err.println(e.getMessage());
                Thread.sleep(1000);
            } catch (InputMismatchException e) {
                // Customize the message for let user know
                System.err.println(Font.useFont(Font.BOLD_RED, "Please only key in integer"));
                input.nextLine();
                Thread.sleep(1000);
            }
        } while (cartOptionError);


    }

}

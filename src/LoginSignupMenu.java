import utility.Font;

import java.util.ArrayList;
import java.util.Scanner;

public class LoginSignupMenu {
    public static void main(String[] args) {
        ArrayList<Customer> customerArrList = new ArrayList<>();
        ArrayList<Admin> adminArrList = new ArrayList<>();

        // calling customer constructors
        Customer customer1 = new Customer("Kelvin", "kelvin@gmail.com", "a123", "012-34567890", "12, Jalan AhKao, Bandar ABC", new Cart());
        customer1.generateCustID();
        Customer customer2 = new Customer("Ali", "ali@gmail.com", "a123", "011-34567890", "11, Jalan AhKao Bandar ABC", new Cart());
        customer2.generateCustID();

        // calling admin constructor
        Admin admin1 = new Admin("Kelvin", "kelvin@gmail.com", "a123", "011-2345678");
        admin1.generateStaffID();
        Admin admin2 = new Admin("Yoshi", "yoshi@gmail.com", "a123", "014-34567890");
        admin2.generateStaffID();

        // creating arrayList of customer
        customerArrList.add(customer1);
        customerArrList.add(customer2);

        //creating arrayList of admin
        adminArrList.add(admin1);
        adminArrList.add(admin2);

        menu();
    }

    public static void menu() {
        Scanner scanner = new Scanner("System.in");
        int loginOrSignup;

        clearScreen();
        System.out.print(Font.TEXT_CYAN);
        System.out.printf(  "%55s", "+--------------------+");
        System.out.printf("%n%55s", "|    Login/Signup    |");
        System.out.printf("%n%55s%n", "+--------------------+");
        System.out.print(Font.RESET);

        System.out.println("Do you wish to Login or Signup");
        System.out.println("1. Login");
        System.out.println("2. Signup");

        System.out.print("Selection: ");

        loginOrSignup = scanner.nextInt();

        switch (loginOrSignup) {
            case 1:
                login();
            case 2:
                signup();
        }
    }

    public static void login() {
        int adminOrCust;
        Scanner scanner = new Scanner("System.in");

        clearScreen();
        System.out.print(Font.TEXT_CYAN);
        System.out.printf(  "%55s", "+-------------+");
        System.out.printf("%n%55s", "|    Login    |");
        System.out.printf("%n%55s%n", "+-----------+");
        System.out.print(Font.RESET);

        System.out.println("Do you wish to login as admin or customer");
        System.out.println("1. Admin");
        System.out.println("2. Customer");
        System.out.println("3. Back to Previous Page");

        System.out.print("Selection: ");
        adminOrCust = scanner.nextInt();

        switch (adminOrCust) {
            case 1:
                adminLogin();
            case 2:
                custLogin();
            case 3:
                menu();
        }
    }

    public static void adminLogin(ArrayList<Admin> adminArrList) {
        String adminInputEmail;
        String adminInputPassword;
        Scanner scanner = new Scanner("System.in");

        System.out.print("Enter Admin Email: ");
        adminInputEmail = scanner.next();
        System.out.print("Enter Admin Password: ");
        adminInputPassword = scanner.next();

        for (int i = 0; i < adminArrList.size(); i++) {
            if (adminArrList.get(i).vldLoginCred(adminInputEmail, adminInputPassword)) {

            }
        }
    }

    public static void custLogin() {

    }

    public static void signup() {

    }

    public static void clearScreen() {
        //    try{
        //        new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        //    }
        //    catch(Exception e){
        //        System.out.println(e);
        //    }
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void pressAnyKeyToContinue(){
        Scanner scanner = new Scanner("System.in");
        scanner.nextLine();
        scanner.nextLine();
    }

}

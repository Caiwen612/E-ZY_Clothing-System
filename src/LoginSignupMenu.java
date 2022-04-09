import utility.Font;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginSignupMenu {
    public static void main(String[] args) throws InterruptedException {
        AuthCodeMultithreading authCodeGenerate = new AuthCodeMultithreading();
        authCodeGenerate.start();

        ArrayList<Customer> customerArrList = new ArrayList<>();
        ArrayList<Admin> adminArrList = new ArrayList<>();

        // calling customer constructors
        Customer customer1 = new Customer("Kelvin", "kelvin@gmail.com", "a123", "012-34567890", "12, Jalan AhKao, Bandar ABC", new Cart(), new Order());
        customer1.generateCustID();
        Customer customer2 = new Customer("Ali", "ali@gmail.com", "a123", "011-34567890", "11, Jalan AhKao Bandar ABC", new Cart(), new Order());
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

        menu(adminArrList, customerArrList);
    }

    private static void menu(ArrayList<Admin> adminArrList, ArrayList<Customer> customerArrList) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        boolean optionVld = true;
        int loginOrSignup;
        do {
            clearScreen();
            System.out.print(Font.TEXT_CYAN);
            System.out.printf(  "%55s", "+--------------------+");
            System.out.printf("%n%55s", "|    Login/Signup    |");
            System.out.printf("%n%55s%n", "+--------------------+");
            System.out.print(Font.RESET);

            System.out.println("Do you wish to Login or Signup");
            System.out.println("1. Login");
            System.out.println("2. Signup");
            System.out.println("3. Exit");

            System.out.print("Selection: ");
            try {
                loginOrSignup = scanner.nextInt();

                switch (loginOrSignup) {
                    case 1:
                        login(adminArrList, customerArrList);
                        break;
                    case 2:
                        signup(adminArrList, customerArrList);
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        optionVld = false;
                        System.out.println(Font.BOLD_RED + "Please Enter the Valid Option.");
                        System.out.print(Font.RESET);
                        Thread.sleep(1000);
                }
            } catch (InputMismatchException inputMismatchException) {
                optionVld = false;
                System.out.println(Font.BOLD_RED + "Please Enter Only Integer.");
                System.out.print(Font.RESET);
                scanner.nextLine();
                Thread.sleep(1000);
            }
        }while (!optionVld);
    }

    private static void login(ArrayList<Admin> adminArrList, ArrayList<Customer> customerArrList) throws InterruptedException {
        int adminOrCust;
        boolean vldOpt = true;
        Scanner scanner = new Scanner(System.in);

        do {
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
            System.out.println("4. Exit");

            System.out.print("Selection: ");
            try {
                adminOrCust = scanner.nextInt();

                switch (adminOrCust) {
                    case 1:
                        adminLogin(adminArrList);
                        break;
                    case 2:
                        custLogin(customerArrList);
                        break;
                    case 3:
                        menu(adminArrList, customerArrList);
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    default:
                        vldOpt = false;
                        System.out.println(Font.BOLD_RED + "Please Enter the Valid Option.");
                        System.out.print(Font.RESET);
                        Thread.sleep(1000);
                }
             } catch (InputMismatchException inputMismatchException) {
                vldOpt = false;
                System.out.println(Font.BOLD_RED + "Please Enter Only Integer.");
                System.out.print(Font.RESET);
                scanner.nextLine();
                Thread.sleep(1000);
            }
        } while (!vldOpt);
    }

    private static void adminLogin(ArrayList<Admin> adminArrList) throws InterruptedException {
        String adminInputEmail;
        String adminInputPassword;
        boolean loginSuccess = false;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println();
            System.out.print(Font.TEXT_CYAN + "Enter Admin Email: ");
            System.out.print(Font.RESET);
            adminInputEmail = scanner.next();
            System.out.print(Font.TEXT_CYAN + "Enter Admin Password: ");
            System.out.print(Font.RESET);
            adminInputPassword = scanner.next();

            for (Admin admin : adminArrList) {
                if (admin.getEmail().equals(adminInputEmail) && admin.getPassword().equals(adminInputPassword)) {
                    loginSuccess = true;
                    break;
                }
            }
            if (loginSuccess) {
                System.out.println(Font.BOLD_GREEN + "Login Successfully");
                System.out.print(Font.RESET);
                Thread.sleep(1000);
                adminMenu();
            }
            else {
                System.out.println(Font.BOLD_RED + "Wrong Credentials, Please Try Again.");
                System.out.print(Font.RESET);
                Thread.sleep(1000);
            }

        } while (!loginSuccess);
    }

    private static void custLogin(ArrayList<Customer> customerArrList) throws InterruptedException {
        String custInputEmail;
        String custInputPassword;
        boolean loginSuccess = false;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println();
            System.out.print(Font.TEXT_CYAN + "Enter Customer Email: ");
            System.out.print(Font.RESET);
            custInputEmail = scanner.next();
            System.out.print(Font.TEXT_CYAN + "Enter Customer Password: ");
            System.out.print(Font.RESET);
            custInputPassword = scanner.next();

            for (Customer customer : customerArrList) {
                if (customer.getEmail().equals(custInputEmail) && customer.getPassword().equals(custInputPassword)) {
                    loginSuccess = true;
                    break;
                }
            }
            if (loginSuccess) {
                System.out.println(Font.BOLD_GREEN + "Login Successfully");
                System.out.print(Font.RESET);
                Thread.sleep(1000);
                custMenu();
            }
            else {
                System.out.println(Font.BOLD_RED + "Wrong Credentials, Please Try Again.");
                System.out.print(Font.RESET);
                Thread.sleep(1000);
            }
        } while (!loginSuccess);

    }

    private static void signup(ArrayList<Admin> adminArrList, ArrayList<Customer> customerArrList) throws InterruptedException {
        int adminOrCust;
        boolean vldOpt = true;
        Scanner scanner = new Scanner(System.in);

        do {
            clearScreen();
            System.out.print(Font.TEXT_CYAN);
            System.out.printf(  "%55s", "+--------------+");
            System.out.printf("%n%55s", "|    Sign Up   |");
            System.out.printf("%n%55s%n", "+--------------+");
            System.out.print(Font.RESET);

            System.out.println("Do you wish to sign up as admin or customer");
            System.out.println("1. Admin");
            System.out.println("2. Customer");
            System.out.println("3. Back to Previous Page");
            System.out.println("4. Exit");

            System.out.print("Selection: ");
            try {
                adminOrCust = scanner.nextInt();

                switch (adminOrCust) {
                    case 1:
                        adminSignUp(adminArrList);
                        break;
                    case 2:
                        customerSignUp();
                        break;
                    case 3:
                        menu(adminArrList, customerArrList);
                        break;
                    case 4:
                        System.exit(0);
                        break;
                    default:
                        vldOpt = false;
                        System.out.println(Font.BOLD_RED + "Please Enter the Valid Option.");
                        System.out.print(Font.RESET);
                        Thread.sleep(1000);
                }
            } catch (InputMismatchException inputMismatchException) {
                vldOpt = false;
                System.out.println(Font.BOLD_RED + "Please Enter Only Integer.");
                System.out.print(Font.RESET);
                scanner.nextLine();
                Thread.sleep(1000);
            }
        } while (!vldOpt);
    }

    private static void adminSignUp(ArrayList<Admin> adminArrList, ArrayList<Customer> customerArrList) throws InterruptedException {
        boolean authValid = false, emailValid = false, passwordValid = false, phoneNoValid = false, confirmPwVld = false;
        String adminName, adminEmail, adminPassword, adminPhoneNo, adminPwConfirm;
        String authCodeInput;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print(Font.TEXT_CYAN + "Please Enter Auth Code: ");
            System.out.print(Font.RESET);

            authCodeInput = scanner.next();
            // clear buffer
            scanner.nextLine();

            if (authCodeInput.equals(Integer.toString(AuthCodeMultithreading.authCode))) {
                authValid = true;
            }
            else {
                System.out.println(Font.BOLD_RED + "Wrong Auth Code Entered, Please Try again.");
                System.out.print(Font.RESET);
                System.out.println();
                Thread.sleep(1000);
            }
        } while (!authValid);

        System.out.print("Name: ");
        adminName = scanner.nextLine();

        do {
            System.out.print("Email: ");
            adminEmail = scanner.next();
            scanner.nextLine();
            if (People.vldEmail(adminEmail)) {
                emailValid = true;
            }
            else {
                System.out.println(Font.BOLD_RED + "Please Enter A Valid Email.");
                System.out.print(Font.RESET);
                System.out.println();
                Thread.sleep(1000);
            }
        } while (!emailValid);

        do {
            System.out.print("Password: ");
            adminPassword = scanner.next();
            scanner.nextLine();

            if (People.vldPassword(adminPassword)) {
                passwordValid = true;
            }
            else {
                System.out.println(Font.BOLD_RED + "Password Must Include Alphabet, Number and At Least 6 Characters.");
                System.out.print(Font.RESET);
                System.out.println();
                Thread.sleep(1000);
            }
        } while (!passwordValid);


        do {
            System.out.print("Confirm Password: ");
            adminPwConfirm = scanner.next();
            scanner.nextLine();
            if (adminPwConfirm.equals(adminPassword)) {
                confirmPwVld = true;
            }
            else {
                System.out.println(Font.BOLD_RED + "The Password Does Not Match with Previous Input");
                System.out.print(Font.RESET);
                System.out.println();
                Thread.sleep(1000);
            }
        } while (!confirmPwVld);

        do {
            System.out.print("Phone Number (999-9999999(9): ");
            adminPhoneNo = scanner.next();
            scanner.nextLine();

            if (People.vldPhoneNo(adminPhoneNo)) {
                phoneNoValid = true;
            }
            else {
                System.out.println(Font.BOLD_RED + "Phone Number Must be in the Format 999-9999999(9)");
                System.out.print(Font.RESET);
                System.out.println();
                Thread.sleep(1000);
            }
        } while (!phoneNoValid);


        Admin newAdmin = new Admin(adminName, adminEmail, adminPassword, adminPhoneNo);
        newAdmin.generateStaffID();
        adminArrList.add(newAdmin);

        // back to login
        login(adminArrList, customerArrList);
    }

    private static void customerSignUp(ArrayList<Admin> adminArrList, ArrayList<Customer> customerArrList) throws InterruptedException {
        boolean emailValid = false, passwordValid = false, phoneNoValid = false, confirmPwVld = false;
        String custName, custEmail, custPassword, custPhoneNo, custPwConfirm, custAddress;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Name: ");
        custName = scanner.nextLine();

        do {
            System.out.print("Email: ");
            custEmail = scanner.next();
            scanner.nextLine();
            if (People.vldEmail(custEmail)) {
                emailValid = true;
            }
            else {
                System.out.println(Font.BOLD_RED + "Please Enter A Valid Email.");
                System.out.print(Font.RESET);
                System.out.println();
                Thread.sleep(1000);
            }
        } while (!emailValid);

        do {
            System.out.print("Password: ");
            custPassword = scanner.next();
            scanner.nextLine();

            if (People.vldPassword(custPassword)) {
                passwordValid = true;
            }
            else {
                System.out.println(Font.BOLD_RED + "Password Must Include Alphabet, Number and At Least 6 Characters.");
                System.out.print(Font.RESET);
                System.out.println();
                Thread.sleep(1000);
            }
        } while (!passwordValid);


        do {
            System.out.print("Confirm Password: ");
            custPwConfirm = scanner.next();
            scanner.nextLine();
            if (custPwConfirm.equals(custPassword)) {
                confirmPwVld = true;
            }
            else {
                System.out.println(Font.BOLD_RED + "The Password Does Not Match with Previous Input");
                System.out.print(Font.RESET);
                System.out.println();
                Thread.sleep(1000);
            }
        } while (!confirmPwVld);

        do {
            System.out.print("Phone Number (999-9999999(9): ");
            custPhoneNo = scanner.next();
            scanner.nextLine();

            if (People.vldPhoneNo(custPhoneNo)) {
                phoneNoValid = true;
            }
            else {
                System.out.println(Font.BOLD_RED + "Phone Number Must be in the Format 999-9999999(9)");
                System.out.print(Font.RESET);
                System.out.println();
                Thread.sleep(1000);
            }
        } while (!phoneNoValid);

        System.out.print("Address: ");
        custAddress = scanner.nextLine();


        Customer newCust = new Customer(custName, custEmail, custPassword, custPhoneNo, custAddress, new Cart(), new Order());
        newCust.generateCustID();
        customerArrList.add(newCust);

        // back to login
        login(adminArrList, customerArrList);
    }

    private static void adminMenu() {
        System.out.println("admin menu");
    }

    private static void custMenu() {
        System.out.println("Customer Menu");
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

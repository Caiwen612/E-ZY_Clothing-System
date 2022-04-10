import utility.Font;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class LoginSignupMenu {
    public static void main(String[] args) throws InterruptedException {
        AuthCodeMultithreading authCodeGenerate = new AuthCodeMultithreading();
        authCodeGenerate.start();

        ArrayList<Customer> customerArrList = new ArrayList<>();
        ArrayList<Admin> adminArrList = new ArrayList<>();
        ArrayList<Product> productArrayList = new ArrayList<>();

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

        // creating arrayList of admin
        adminArrList.add(admin1);
        adminArrList.add(admin2);

        // array list of product
//        productArrayList.add(ProductMixCart.productArraylist.get(1).clone());

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
                    case 1 -> login(adminArrList, customerArrList);
                    case 2 -> signup(adminArrList, customerArrList);
                    case 3 -> System.exit(0);
                    default -> {
                        optionVld = false;
                        System.out.println(Font.BOLD_RED + "Please Enter the Valid Option.");
                        System.out.print(Font.RESET);
                        Thread.sleep(1000);
                    }
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
                    case 1 -> adminLogin(adminArrList, customerArrList);
                    case 2 -> custLogin(adminArrList, customerArrList);
                    case 3 -> menu(adminArrList, customerArrList);
                    case 4 -> System.exit(0);
                    default -> {
                        vldOpt = false;
                        System.out.println(Font.BOLD_RED + "Please Enter the Valid Option.");
                        System.out.print(Font.RESET);
                        Thread.sleep(1000);
                    }
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

    private static void adminLogin(ArrayList<Admin> adminArrList, ArrayList<Customer> customerArrList) throws InterruptedException {
        int index = 0;
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

            for (int i = 0; i < adminArrList.size(); i++) {
                if (adminArrList.get(i).getEmail().equals(adminInputEmail) && adminArrList.get(i).getPassword().equals(adminInputPassword)) {
                    loginSuccess = true;
                    index = i;
                    break;
                }
            }
            if (loginSuccess) {
                System.out.println(Font.BOLD_GREEN + "Login Successfully");
                System.out.print(Font.RESET);
                Thread.sleep(1000);
                adminMenu(adminArrList, customerArrList, adminArrList.get(index));
            }
            else {
                System.out.println(Font.BOLD_RED + "Wrong Credentials, Please Try Again.");
                System.out.print(Font.RESET);
                Thread.sleep(1000);
            }

        } while (!loginSuccess);
    }

    private static void custLogin(ArrayList<Admin> adminArrList, ArrayList<Customer> customerArrList) throws InterruptedException {
        int index = 0;
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

            for (int i = 0; i < customerArrList.size(); i++) {
                if (customerArrList.get(i).getEmail().equals(custInputEmail) && customerArrList.get(i).getPassword().equals(custInputPassword)) {
                    loginSuccess = true;
                    index = i;
                    break;
                }
            }
            if (loginSuccess) {
                System.out.println(Font.BOLD_GREEN + "Login Successfully");
                System.out.print(Font.RESET);
                Thread.sleep(1000);
                custMenu(adminArrList, customerArrList, customerArrList.get(index));
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
                    case 1 -> adminSignUp(adminArrList, customerArrList);
                    case 2 -> customerSignUp(adminArrList, customerArrList);
                    case 3 -> menu(adminArrList, customerArrList);
                    case 4 -> System.exit(0);
                    default -> {
                        vldOpt = false;
                        System.out.println(Font.BOLD_RED + "Please Enter the Valid Option.");
                        System.out.print(Font.RESET);
                        Thread.sleep(1000);
                    }
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

    public static void adminMenu(ArrayList<Admin> adminArrList, ArrayList<Customer> customerArrList, Admin admin) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int selection;
        boolean optionVld = true;
        String productID;
        char productFilter;

        do {
            clearScreen();
            System.out.print(Font.TEXT_CYAN);
            System.out.printf(  "%55s", "+------------------+");
            System.out.printf("%n%55s", "|    Admin Menu    |");
            System.out.printf("%n%55s%n", "+------------------+");
            System.out.print(Font.RESET);
            System.out.println("Currently Logged In As " + Font.BOLD_YELLOW + admin.getName());
            System.out.print(Font.RESET);
            System.out.println("Staff ID: " + Font.BOLD_YELLOW + admin.getStaffID());
            System.out.print(Font.RESET);
            System.out.println();

            System.out.println("1. Edit Products");
            System.out.println("2. Order Products From Supplier");
            System.out.println("3. Check Stock Quantity");
            System.out.println("4. Sign Out");
            System.out.println("5. Exit");

            System.out.print("Selection: ");
            try {
                selection = scanner.nextInt();
                scanner.nextLine();


                switch (selection) {
                    case 1:
                        // edit products
                        System.out.print("Please Enter Product ID: ");
                        productID = scanner.next();
                        scanner.nextLine();

                        productFilter = productID.charAt(0);

//                        ProductMixCart.linearSearchByProductID(productArrayList, productID, productFilter);
                        break;
                    case 2:
                        // increment by id or name
                        break;
                    case 3:
                        // check stock qty
                        break;
                    case 4:
                        System.out.println(Font.TEXT_CYAN + "You Are Signed Out.");
                        Thread.sleep(1000);
                        menu(adminArrList, customerArrList);
                        break;
                    case 5:
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
        } while (!optionVld);

    }

    public static void custMenu(ArrayList<Admin> adminArrList, ArrayList<Customer> customerArrList, Customer customer) throws InterruptedException {
        int selection;
        boolean vldOpt = true;
        Scanner scanner = new Scanner(System.in);

        do {
            clearScreen();
            System.out.print(Font.TEXT_CYAN);
            System.out.printf(  "%55s", "+--------------------+");
            System.out.printf("%n%55s", "|    Customer Menu   |");
            System.out.printf("%n%55s%n", "+-------------------+");
            System.out.print(Font.RESET);

            System.out.println("1. Profile");
            System.out.println("2. Make Order");
            System.out.println("3. Cart");
            System.out.println("4. Order History");
            System.out.println("5. Payment");
            System.out.println("6. Sign Out");
            System.out.println("7. Exit");

            System.out.print("Selection: ");
            try {
                selection = scanner.nextInt();

                switch (selection) {
                    case 1 -> custProfile(adminArrList, customerArrList, customer);
//                    case 2 -> ProductMixCart.productMenu(productArrayList, cart);
//                    case 3 -> menu(adminArrList, customerArrList);
                    case 4 -> System.exit(0);
                    default -> {
                        vldOpt = false;
                        System.out.println(Font.BOLD_RED + "Please Enter the Valid Option.");
                        System.out.print(Font.RESET);
                        Thread.sleep(1000);
                    }
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

    public static void custProfile(ArrayList<Admin> adminArrList, ArrayList<Customer> customerArrList, Customer customer) throws InterruptedException {
        int option = 0;
        boolean optionVld = true;
        Scanner scanner = new Scanner(System.in);

        do {
            LoginSignupMenu.clearScreen();
            System.out.print(Font.TEXT_CYAN);
            System.out.printf(  "%55s", "+-------------+");
            System.out.printf("%n%55s", "|    Profile    |");
            System.out.printf("%n%55s%n", "+-----------+");
            System.out.print(Font.RESET);

            System.out.println();
            System.out.println("Customer ID: " + Font.BOLD_YELLOW + customer.getCustomerID() + Font.RESET);
            System.out.println("Name: " + Font.BOLD_YELLOW + customer.getName() + Font.RESET);
            System.out.println("Email: " + Font.BOLD_YELLOW + customer.getEmail() + Font.RESET);
            System.out.println("Phone No: " + Font.BOLD_YELLOW + customer.getPhoneNo() + Font.RESET);
            System.out.println("Address: " + Font.BOLD_YELLOW + customer.getAddress() + Font.RESET);

            System.out.println();
            System.out.println("1. Edit Name");
            System.out.println("2. Edit Email");
            System.out.println("3. Edit Password");
            System.out.println("4. Edit Phone No");
            System.out.println("5. Edit Address");
            System.out.println("6. Back to Previous Page");
            System.out.println("7. Exit");

            try {
                option = scanner.nextInt();

                switch (option) {
                    case 1 -> editCustName(customer);
                    case 2 -> editCustEmail(customer);
                    case 3 -> editCustPassword(customer);
//                    case 4 -> editPhoneNo();
//                    case 5 -> editAddress();
                    case 7 -> LoginSignupMenu.custMenu(adminArrList, customerArrList, customer);
                    default -> {
                        optionVld = false;
                        System.out.println(Font.BOLD_RED + "Please Enter the Valid Option.");
                        System.out.print(Font.RESET);
                        Thread.sleep(1000);
                    }
                }
            } catch (InputMismatchException inputMismatchException) {
                optionVld = false;
                System.out.println(Font.BOLD_RED + "Please Enter Only Integer.");
                System.out.print(Font.RESET);
                scanner.nextLine();
                Thread.sleep(1000);
            }
        } while (!optionVld);

    }

    public static void editCustName(ArrayList<Admin> adminArrList, ArrayList<Customer> customerArrList, Customer customer) throws InterruptedException {
        String name;
        Scanner scanner = new Scanner(System.in);

        System.out.print("New Customer Name (type exit to stop): ");
        name = scanner.nextLine();

        if (name.equalsIgnoreCase("exit")) {
            custProfile(adminArrList, customerArrList, customer);
        }

        customer.setName(name);

        System.out.println(Font.BOLD_GREEN + "Name Changed." + Font.RESET);
        Thread.sleep(1000);
    }

    public static void editCustEmail(ArrayList<Admin> adminArrList, ArrayList<Customer> customerArrList, Customer customer) throws InterruptedException {
        String email;
        boolean emailVld = true;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("New Customer Email (type exit to stop): ");
            email = scanner.next();
            scanner.nextLine();

            if (email.equalsIgnoreCase("exit")) {
                custProfile(adminArrList, customerArrList, customer);
            }

            if (People.vldEmail(email)) {
                customer.setEmail(email.toLowerCase());
                System.out.println(Font.BOLD_GREEN + "Email Changed." + Font.RESET);
                Thread.sleep(1000);
            }
            else {
                emailVld = false;
                System.out.println(Font.BOLD_RED + "Please Enter A Valid Email");
                System.out.print(Font.RESET);
                scanner.nextLine();
                Thread.sleep(1000);
            }
        } while (!emailVld);

    }

    public static void editCustPassword(ArrayList<Admin> adminArrList, ArrayList<Customer> customerArrList, Customer customer) throws InterruptedException {
        String password;
        String authCode;
        boolean passwordVld = false;
        boolean authValid = false;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Auth Code (type exit to stop): ");
            authCode = scanner.next();
            scanner.nextLine();

            if (authCode.equalsIgnoreCase("exit")) {
                custProfile(adminArrList, customerArrList, customer);
            }

            if (authCode.equals(Integer.toString(AuthCodeMultithreading.authCode))) {
                authValid = true;
            }
            else {
                System.out.println(Font.BOLD_RED + "Wrong Auth Code Entered, Please Try again.");
                System.out.print(Font.RESET);
                System.out.println();
                Thread.sleep(1000);
            }
        } while (!authValid);

        do {
            System.out.print("New Customer Password: ");
            password = scanner.next();
            scanner.nextLine();

            if (People.vldPassword(password)) {
                passwordVld = true;
                customer.setPassword(password);
                System.out.println(Font.BOLD_GREEN + "Password Changed." + Font.RESET);
                Thread.sleep(1000);
            }
            else {
                System.out.println(Font.BOLD_RED + "Password Must Include Alphabet, Number and At Least 6 Characters.");
                System.out.print(Font.RESET);
                scanner.nextLine();
                Thread.sleep(1000);
            }
        } while (!passwordVld);

    }

    public static void editPhoneNo() {
        
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
}

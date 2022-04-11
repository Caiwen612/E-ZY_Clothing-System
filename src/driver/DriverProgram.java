package driver;

import cart.Cart;
import order.Order;
import payment.Bank;
import payment.DebitCredit;
import payment.EWallet;
import payment.Payment;
import product.*;
import user.Admin;
import user.AuthCodeMultithreading;
import user.Customer;
import user.People;
import utility.Font;
import utility.Validation;
import utility.ValidationException;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class DriverProgram {
    //Declare a set of arrayList to store in database
    public static ArrayList<Product> productArrayList = new ArrayList<>();
    public static ArrayList<Customer> customerArrList = new ArrayList<>();
    public static ArrayList<Admin> adminArrList = new ArrayList<>();
    public static ArrayList<Payment> payment = new ArrayList<>();
    public static ArrayList<Order> orderArrList = new ArrayList<>();

    //Declare a static method
    static Scanner input = new Scanner(System.in);
    static Scanner scanner = new Scanner(System.in);
    static DecimalFormat df2 = new DecimalFormat("0.00");

    public static void main(String[] args) throws InterruptedException {
        welcome();
        //Generate auth code for sensitive action purpose
        AuthCodeMultithreading authCodeGenerate = new AuthCodeMultithreading();
        authCodeGenerate.start();
        //Create files
        databaseInit();
        //Load the arrayList
        loadArrayList();
        //Initialize the arrayList if database is empty
        if(productArrayList.size() == 0){
            System.out.println("No product in the database");
            System.out.println("Default data is use to display");
            setProducts(productArrayList);
            setUser(adminArrList, customerArrList);
        }
        //Check Database(DEBUG)
        productArrayList.forEach(System.out::println);
        customerArrList.forEach(System.out::println);
        adminArrList.forEach(System.out::println);
        payment.forEach(System.out::println);
        orderArrList.forEach(System.out::println);

        menu(adminArrList, customerArrList);
    }

    //TODO: Welcome @TEAM
    public static void welcome(){
        System.out.println("Welcome to the e-commerce system");
        logo();
        System.out.printf("%79s %s%n", "", "System Developer");
        System.out.printf("%75s %s%n", "", "+=====================+");
        System.out.printf("%75s %s%n", "", "|Application Developer|");
        System.out.printf("%75s %s%n", "", "+=====================+");
        System.out.printf("%80s %s%n", "", "Tay Chai Boon");
        System.out.printf("%78s %s%n", "", "Eugene Law Kai Le");
        System.out.printf("%77s %s%n", "", "Kelvin Ee Wei Keong");

        //Display date and times
        System.out.println();
        System.out.printf("%75s%s%n", "", new Date());

        System.out.printf("%75s", "");
        pressAnyKeyToContinue();
    }

    public static void logo(){
        System.out.printf("%45s %s%n", "", Font.TEXT_CYAN + " .----------------.  .----------------.  .----------------.  .----------------.    ");
        System.out.printf("%45s %s%n", "", "| .--------------. || .--------------. || .--------------. || .--------------. |   ");
        System.out.printf("%45s %s%n", "", "| |  _________   | || |              | || |   ________   | || |  ____  ____  | |   ");
        System.out.printf("%45s %s%n", "", "| | |_   ___  |  | || |              | || |  |  __   _|  | || | |_  _||_  _| | |   ");
        System.out.printf("%45s %s%n", "", "| |   | |_  \\_|  | || |    ______    | || |  |_/  / /    | || |   \\ \\  / /   | |");
        System.out.printf("%45s %s%n", "", "| |   |  _|  _   | || |   |______|   | || |     .'.' _   | || |    \\ \\/ /    | | ");
        System.out.printf("%45s %s%n", "", "| |  _| |___/ |  | || |              | || |   _/ /__/ |  | || |    _|  |_    | |   ");
        System.out.printf("%45s %s%n", "", "| | |_________|  | || |              | || |  |________|  | || |   |______|   | |   ");
        System.out.printf("%45s %s%n", "", "| |              | || |              | || |              | || |              | |   ");
        System.out.printf("%45s %s%n", "", "| '--------------' || '--------------' || '--------------' || '--------------' |   ");
        System.out.printf("%45s %s%n", "", "'----------------'  '----------------'  '----------------'  '----------------'     ");
        System.out.print(Font.RESET);
    }

    //TODO: Simple Database @Author TAY CHAI BOON
    //Create database
    public static void databaseInit(){
        try {
            File file = new File("database/product.ser");
            if (!file.exists()) {
                file.createNewFile();
            }
            File file2 = new File("database/customer.ser");
            if (!file2.exists()) {
                file2.createNewFile();
            }
            File file3 = new File("database/admin.ser");
            if (!file3.exists()) {
                file3.createNewFile();
            }
            File file4 = new File("database/payment.ser");
            if (!file4.exists()) {
                file4.createNewFile();
            }
            File file5 = new File("database/order.ser");
            if (!file5.exists()) {
                file5.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Load arrayList from files
    public static void loadArrayList() {
        File[] files = new File("database").listFiles();
        for (File file : files) {
            System.out.println(file.getName());
            if (file.exists()) { //If the files exists, load all data into driver program
                try {
                    FileInputStream fis = new FileInputStream("database/" + file.getName());
                    ObjectInputStream ois = new ObjectInputStream(fis);

                    switch (file.getName()) {
                        case "product.ser" -> productArrayList = (ArrayList<Product>) ois.readObject();
                        case "customer.ser" -> customerArrList = (ArrayList<Customer>) ois.readObject();
                        case "admin.ser" -> adminArrList = (ArrayList<Admin>) ois.readObject();
                        case "payment.ser" -> payment = (ArrayList<Payment>) ois.readObject();
                        case "order.ser" -> orderArrList = (ArrayList<Order>) ois.readObject();
                        default -> System.out.println("Unknown file to load into arraylist " + file.getName());
                    }
                    ois.close();
                    System.out.print(Font.TEXT_YELLOW);
                    System.out.println("The progress of the programs is loaded from the database.");
                    System.out.print(Font.RESET);
                }  catch (EOFException e){
                    //First time use this systems
                    System.out.print(Font.TEXT_YELLOW);
                    System.out.println("The database is empty");
                    System.out.println("Your progress will store in database.");
                    System.out.print(Font.RESET);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    //Store arraylist from files
    public static void storeArrayList() {
        File[] files = new File("database").listFiles();

        for (File file : files) {
            if (file.exists()) { //If the files exists, load all data into driver program
                try {
                    FileOutputStream fos = new FileOutputStream("database/" + file.getName());
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    switch (file.getName()) {
                        case "product.ser" -> oos.writeObject(productArrayList);
                        case "customer.ser" -> oos.writeObject(customerArrList);
                        case "admin.ser" -> oos.writeObject(adminArrList);
                        case "payment.ser" -> oos.writeObject(payment);
                        case "order.ser" -> oos.writeObject(orderArrList);
                        default -> System.out.println("Unknown file stored in database to be serialize in " + file.getName());
                    }
                    oos.close();
//                    System.out.println("Your progress has been store in database.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.print(Font.TEXT_YELLOW);
        System.out.println("Your progress has been store in database.");
    }

    //TODO: Initialize the arrayList @Author TAY CHAI BOON
    private static void setUser(ArrayList<Admin> adminArrList ,ArrayList<Customer> customerArrList) {
        // calling customer constructor
        Customer customer1 = new Customer("Kelvin", "kelvin@gmail.com", "c12345", "012-34567890", "12, Jalan AhKao, Bandar ABC");
        customer1.generateCustID();
        Customer customer2 = new Customer("Ali", "ali@gmail.com", "c67890", "011-34567890", "11, Jalan AhKao Bandar ABC");
        customer2.generateCustID();

        // calling admin constructor
        Admin admin1 = new Admin("Kelvin", "kelvin@gmail.com", "a12345", "011-2345678");
        admin1.generateStaffID();
        Admin admin2 = new Admin("Yoshi", "yoshi@gmail.com", "a67890", "014-34567890");
        admin2.generateStaffID();

        customerArrList.add(customer1);
        customerArrList.add(customer2);

        // creating arrayList of admin
        adminArrList.add(admin1);
        adminArrList.add(admin2);

    }

    private static void setProducts(ArrayList<Product> productArrayList) {
        //Shirt
        Product p1 = new Shirt("Crew Tee", 25.30, 20, 3);
        Product p2 = new Shirt("Hoodie", 50.20, 10, 4);
        Product p3 = new Shirt("Jacket", 67.70, 30, 2);
        Product p4 = new Shirt("Poplin Shirt", 43.80, 40, 4);
        Product p5 = new Shirt("Dress", 40.50, 30, 5);
        Product p6 = new Shirt("Jumpsuit", 70.60, 25, 3);
        Product p7 = new Shirt("Sport wear", 80.30, 66, 5);
        //Pant
        Product p8 = new Pant("Jeans", 30.50, 12, 5);
        Product p9 = new Pant("Trousers", 40.20, 5, 5);
        Product p10 = new Pant("Cotton pants", 35.30, 20, 4);
        Product p11 = new Pant("Short pan", 15.60, 26, 3);
        Product p12 = new Pant("Ripped pants", 43.20, 19, 2);
        Product p13 = new Pant("Sport pan", 30.60, 45, 4);
        Product p14 = new Pant("Cropped pant", 39.20, 45, 1);
        //Accessories
        Product p15 = new Accessories("Cap", 14.60, 30, 2);
        Product p16 = new Accessories("Short wallet", 20.60, 25, 3);
        Product p17 = new Accessories("Long wallet", 30.60, 17, 2);
        Product p18 = new Accessories("Earring", 10.30, 30, 5);
        Product p19 = new Accessories("Headband", 15.40, 50, 4);
        Product p20 = new Accessories("Sunglasses", 12.50, 45, 3);
        //Shoe
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

    //TODO: CUSTOMER AND ADMIN @Author Kelvin Ee Wei Keong
    private static void menu(ArrayList<Admin> adminArrList, ArrayList<Customer> customerArrList) throws InterruptedException {

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
                    case 3 -> endProgram();
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
            System.out.printf("%n%55s%n", "+-------------+");
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
                    case 4 -> endProgram();
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
                    case 4 -> endProgram();
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
            System.out.print("Phone Number (999-9999999(9)): ");
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


        Customer newCust = new Customer(custName, custEmail, custPassword, custPhoneNo, custAddress);
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
        Object obj;

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
            System.out.println("4. Generate Sales Report");
            System.out.println("5. Sign Out");
            System.out.println("6. Exit");

            System.out.print("Selection: ");
            try {
                selection = scanner.nextInt();
                scanner.nextLine();


                switch (selection) {
                    case 1:
                        // edit products
                        System.out.print("Please Enter Product ID: " + Font.TEXT_YELLOW);
                        productID = scanner.next();
                        System.out.print(Font.RESET);
                        scanner.nextLine();

                        productFilter = productID.charAt(0);
                        obj = linearSearchByProductID(productArrayList, productID, productFilter);
                        admin.modifyProduct(obj,adminArrList, customerArrList,admin);
                        break;
                    case 2:
                        // increment quantity
                        System.out.print("Please Enter Product ID: " + Font.TEXT_YELLOW);
                        productID = scanner.next();
                        System.out.print(Font.RESET);
                        scanner.nextLine();
                        productFilter = productID.charAt(0);
                        obj = linearSearchByProductID(productArrayList, productID, productFilter);
                        admin.addStock(obj, adminArrList, customerArrList, admin);
                        break;
                    case 3:
                        // check stock qty
                        System.out.print("Please Enter Product ID: " + Font.TEXT_YELLOW);
                        productID = scanner.next();
                        System.out.print(Font.RESET);
                        scanner.nextLine();
                        productFilter = productID.charAt(0);
                        obj = linearSearchByProductID(productArrayList, productID, productFilter);
                        Admin.checkQuantity(obj, adminArrList, customerArrList, admin);
                        break;

                    case 4:
                        // generate sales report
                        Admin.generateReport(orderArrList, adminArrList, customerArrList, admin);
                        break;
                    case 5:
                        System.out.println(Font.TEXT_CYAN + "You Are Signed Out.");
                        Thread.sleep(1000);
                        menu(adminArrList, customerArrList);
                        break;
                    case 6:
                        endProgram();
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

    // incomplete
    public static void custMenu(ArrayList<Admin> adminArrList, ArrayList<Customer> customerArrList, Customer customer) throws InterruptedException {
        Cart cart = customer.getCart();
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
                    case 2 -> productMenu(productArrayList, cart,customer);
                    case 3 -> cartMenu(productArrayList, cart,customer);
                    case 4 -> orderHistory(customer);
                    case 5 -> paymentMenu(cart, payment,customer);
                    case 6 -> login(adminArrList, customerArrList);
                    case 7 -> endProgram();
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
        int option;
        boolean optionVld = true;
        Scanner scanner = new Scanner(System.in);

        do {
            clearScreen();
            System.out.print(Font.TEXT_CYAN);
            System.out.printf(  "%55s", "+---------------+");
            System.out.printf("%n%55s", "|    Profile    |");
            System.out.printf("%n%55s%n", "+--------------+");
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

            System.out.println();
            System.out.print("Selection: ");
            try {
                option = scanner.nextInt();

                switch (option) {
                    case 1 -> editCustName(adminArrList, customerArrList, customer);
                    case 2 -> editCustEmail(adminArrList, customerArrList, customer);
                    case 3 -> editCustPassword(adminArrList, customerArrList, customer);
                    case 4 -> editPhoneNo(adminArrList, customerArrList, customer);
                    case 5 -> editAddress(adminArrList, customerArrList, customer);
                    case 6 -> custMenu(adminArrList, customerArrList, customer);
                    case 7 -> endProgram();
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

        custProfile(adminArrList, customerArrList, customer);
    }

    public static void editCustEmail(ArrayList<Admin> adminArrList, ArrayList<Customer> customerArrList, Customer customer) throws InterruptedException {
        String email;
        boolean emailVld;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("New Customer Email (type exit to stop): ");
            email = scanner.next();
            scanner.nextLine();

            if (email.equalsIgnoreCase("exit")) {
                custProfile(adminArrList, customerArrList, customer);
            }

            if (People.vldEmail(email)) {
                emailVld = true;
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
        custProfile(adminArrList, customerArrList, customer);
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
        custProfile(adminArrList, customerArrList, customer);
    }

    public static void editPhoneNo(ArrayList<Admin> adminArrList, ArrayList<Customer> customerArrList, Customer customer) throws InterruptedException {
        String phoneNo;
        boolean phoneNoVld = true;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("New Customer Phone No (type exit to stop): ");
            phoneNo = scanner.next();
            scanner.nextLine();

            if (phoneNo.equalsIgnoreCase("exit")) {
                custProfile(adminArrList, customerArrList, customer);
            }

            if (People.vldPhoneNo(phoneNo)) {
                phoneNoVld = true;
                customer.setPhoneNo(phoneNo);
                System.out.println(Font.BOLD_GREEN + "Phone Number Changed." + Font.RESET);
                Thread.sleep(1000);
            }
            else {
                phoneNoVld = false;
                System.out.println(Font.BOLD_RED + "Please Enter A Valid Phone Number (999-9999999999)");
                System.out.print(Font.RESET);
                scanner.nextLine();
                Thread.sleep(1000);
            }
        } while (!phoneNoVld);
        custProfile(adminArrList, customerArrList, customer);
    }

    public static void editAddress(ArrayList<Admin> adminArrList, ArrayList<Customer> customerArrList, Customer customer) throws InterruptedException {
        String address;
        Scanner scanner = new Scanner(System.in);

        System.out.print("New Customer Address (type exit to stop): ");
        address = scanner.nextLine();

        if (address.equalsIgnoreCase("exit")) {
            custProfile(adminArrList, customerArrList, customer);
        }

        customer.setAddress(address);

        System.out.println(Font.BOLD_GREEN + "Address Changed." + Font.RESET);
        Thread.sleep(1000);
        custProfile(adminArrList, customerArrList, customer);
    }

    //TODO: PRODUCT + CART @Author TAY CHAI BOON
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~TODO: Cart Menu ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public static void cartMenu(ArrayList<Product> productArrayList, Cart cart,Customer customer) throws InterruptedException {
        //Create a new cart
        clearScreen();
        System.out.print(Font.TEXT_CYAN);
        System.out.printf(  "%55s",  "+------------+");
        System.out.printf("%n%55s","|    Cart    |");
        System.out.printf("%n%55s","+------------+");
        System.out.print(Font.RESET);

        //Display cart
        System.out.println();
        System.out.println(" +=================================================================================================+   ");
        System.out.print(" |");
        //Setup for cart title
        System.out.printf("%12s", "Cart No  |");
        System.out.printf("%7s", "ID  |");
        System.out.printf("%21s", "Name        |");
        System.out.printf("%14s", "Price    |");
        System.out.printf("%8s", "Qty  |");
        System.out.printf("%13s", "Rating   |");
        System.out.printf("%24s", "Total per Item    |\n");
        System.out.print(" +=================================================================================================+   ");
        System.out.println();
        cart.displayItem();
        System.out.printf(  "%60s",  "[1] Add Item       ");
        System.out.printf("%n%60s",  "[2] Edit Item      ");
        System.out.printf("%n%60s",  "[3] Remove Item    ");
        System.out.printf("%n%60s",  "[4] Sort Item      ");
        System.out.printf("%n%60s",  "[5] Make Payment   ");
        System.out.printf("%n%60s",  "[6] Back           ");

        // Get input for cart option
        boolean cartOptionError = true;
        int cartOption = 0;
        do {
            try {
                System.out.print(Font.TEXT_BLUE);
                System.out.printf("%n%54s",  "Enter your option: ");
                cartOption = input.nextInt();
                System.out.print(Font.RESET);
                Validation.validOption(cartOption, 1, 6);
                cartOptionError = false;
            } catch (ValidationException e) {
                System.err.println(e.getMessage());
                Thread.sleep(1000);
            } catch (InputMismatchException e) {
                System.err.println(Font.useFont(Font.BOLD_RED, "Please only key in integer"));
                input.nextLine();
                Thread.sleep(1000);
            }
        } while (cartOptionError);
        switch (cartOption) {
            case 1:
                addItem(productArrayList, cart,customer);
                break;
            case 2:
                editItem(productArrayList,cart,customer);
                break;
            case 3:
                removeItem(productArrayList,cart,customer);
                break;
            case 4:
                sortItem(productArrayList,cart,customer);
                break;
            case 5:
                paymentMenu(cart, payment,customer);
                break;
            case 6:
                custMenu(adminArrList, customerArrList, customer);
                break;
        }
    }

    public static void addItem(ArrayList<Product> productArrayList, Cart cart, Customer customer) throws InterruptedException {
        productMenu(productArrayList,cart,customer);
    }

    public static void editItem(ArrayList<Product> productArrayList, Cart cart, Customer customer) throws InterruptedException {
        System.out.println(Font.TEXT_YELLOW);
        System.out.printf("%75s","Key in the cart no which you wish to edit the quantity. ^-^");
        boolean itemIndexError = true;
        int itemIndex = 0;
        do {
            try {
                System.out.print(Font.TEXT_BLUE);
                System.out.printf("%n%54s",  "Enter the cart no: ");
                itemIndex = input.nextInt();
                Validation.validOption(itemIndex, 1, cart.getCartItem().size());
                itemIndexError = false;
            } catch (ValidationException e) {
                System.err.println(e.getMessage());
                Thread.sleep(1000);
            } catch (InputMismatchException e) {
                System.err.println(Font.useFont(Font.BOLD_RED, "Please only key in integer"));
                input.nextLine();
                Thread.sleep(1000);
            }
        } while (itemIndexError);
        boolean editItemError = true;
        char editItem = 'A';
        do {
            try {
                System.out.print(Font.TEXT_BLUE);
                System.out.printf("%62s","Do you want to change this? (Y/N) ");
                editItem = Character.toUpperCase(input.next().charAt(0));
                Validation.validCharYN(editItem);
                editItemError = false;
            } catch (ValidationException e) {
                System.err.println(e.getMessage());
                input.nextLine();
                Thread.sleep(1000);
            }
        } while (editItemError);
        if (editItem == 'Y') {
            String itemName = cart.getProduct(itemIndex).getName();
            System.out.println(Font.TEXT_YELLOW);
            Product targetProduct = linearSearchByCartItemName(productArrayList, itemName);
            System.out.printf("%62s",("Available quantity for " + itemName +" :" +  targetProduct.getQty()));
            boolean quantityOptionError = true;
            int quantityOption = 0;
            do {
                try {
                    System.out.println(Font.TEXT_BLUE);
                    System.out.printf("%55s","Enter the new quantity: ");
                    quantityOption = input.nextInt();
                    Validation.validProductQuantity(quantityOption, targetProduct.getQty());
                    quantityOptionError = false;
                } catch (ValidationException e) {
                    System.err.println(e.getMessage());
                    Thread.sleep(1000);
                } catch (InputMismatchException e) {
                    System.err.println(Font.useFont(Font.BOLD_RED, "Please only key in integer"));
                    input.nextLine();
                    Thread.sleep(1000);
                }
            } while (quantityOptionError);
            cart.editItem(itemIndex,quantityOption);
            System.out.printf("%n%63s","Press enter key to go back Cart menu");
            pressAnyKeyToContinue();
            cartMenu(productArrayList,cart,customer);
        } else{
            System.out.println(Font.TEXT_YELLOW);
            System.out.printf("%63s","Press enter key to go back Cart menu");
            pressAnyKeyToContinue();
            cartMenu(productArrayList,cart,customer);
        }

    }

    public static void removeItem(ArrayList<Product> productArrayList, Cart cart, Customer customer) throws InterruptedException {
        System.out.println(Font.TEXT_YELLOW);
        System.out.printf("%70s","Key in the cart no which you wish to remove. ^-^");
        boolean itemIndexError = true;
        int itemIndex = 0;
        do {
            try {
                System.out.print(Font.TEXT_BLUE);
                System.out.printf("%n%54s",  "Enter the cart no: ");
                itemIndex = input.nextInt();
                Validation.validOption(itemIndex, 1, cart.getCartItem().size());
                itemIndexError = false;
            } catch (ValidationException e) {
                System.err.println(e.getMessage());
                Thread.sleep(1000);
            } catch (InputMismatchException e) {
                System.err.println(Font.useFont(Font.BOLD_RED, "Please only key in integer"));
                input.nextLine();
                Thread.sleep(1000);
            }
        } while (itemIndexError);
        boolean removeItemError = true;
        char removeItem = 'A';
        do {
            try {
                System.out.print(Font.TEXT_BLUE);
                System.out.printf("%62s","Do you want to remove this? (Y/N) ");
                removeItem = Character.toUpperCase(input.next().charAt(0));
                Validation.validCharYN(removeItem);
                removeItemError = false;
            } catch (ValidationException e) {
                System.err.println(e.getMessage());
                input.nextLine();
                Thread.sleep(1000);
            }
        } while (removeItemError);
        if (removeItem == 'Y') {
            cart.removeItem(itemIndex);
            System.out.printf("%n%63s","Press enter key to go back Cart menu");
            pressAnyKeyToContinue();
            cartMenu(productArrayList,cart,customer);

        } else{
            System.out.println(Font.TEXT_YELLOW);
            System.out.printf("%63s","Press enter key to go back Cart menu");
            pressAnyKeyToContinue();
            cartMenu(productArrayList,cart,customer);
        }
    }

    public static void sortItem(ArrayList<Product> productArrayList, Cart cart, Customer customer) throws InterruptedException {
        clearScreen();
        System.out.print(Font.TEXT_CYAN);
        System.out.printf(  "%55s",  "+------------+");
        System.out.printf("%n%55s","|    Sort    |");
        System.out.printf("%n%55s","+------------+");
        System.out.print(Font.RESET);
        //Interface (Comparator)
        System.out.println(Font.TEXT_YELLOW);
        System.out.printf("%n%67s","[ASC] Ascending     [DESC] Descending");
        System.out.print(Font.RESET);
        System.out.println();
        System.out.printf("%n%70s", ("[1] Sort by name " + Font.TEXT_YELLOW + "[ASC]" + Font.RESET));
        System.out.printf("%n%71s",("[2] Sort by name " + Font.TEXT_YELLOW + "[DESC]" + Font.RESET));
        System.out.printf("%n%77s",("[3] Sort by total price " + Font.TEXT_YELLOW + "[ASC]" + Font.RESET));
        System.out.printf("%n%78s",("[4] Sort by total price " + Font.TEXT_YELLOW + "[DESC]" + Font.RESET));
        System.out.printf("%n%74s",("[5] Sort by quantity " + Font.TEXT_YELLOW + "[ASC]" + Font.RESET));
        System.out.printf("%n%75s",("[6] Sort by quantity " + Font.TEXT_YELLOW + "[DESC]" + Font.RESET));
        System.out.printf("%n%85s",("[7] Sort by qty and total price " + Font.TEXT_YELLOW + "[ASC]" + Font.RESET));
        System.out.printf("%n%86s",("[8] Sort by qty and total price " + Font.TEXT_YELLOW + "[DESC]" + Font.RESET));
        System.out.printf("%n%61s","[9] Back to Cart Menu ");

        boolean sortOptionError = true;
        int sortOption = 0;
        do {
            try {
                System.out.print(Font.TEXT_BLUE);
                System.out.printf("%n%n%58s",  "Enter sort option: ");
                sortOption = input.nextInt();
                Validation.validOption(sortOption, 1, 9);
                sortOptionError = false;
            } catch (ValidationException e) {
                System.err.println(e.getMessage());
                Thread.sleep(1000);
            } catch (InputMismatchException e) {
                System.err.println(Font.useFont(Font.BOLD_RED, "Please only key in integer"));
                input.nextLine();
                Thread.sleep(1000);
            }
        } while (sortOptionError);
        switch (sortOption) {
            case 1:
                cart.sortByNameAscending();
                break;
            case 2:
                cart.sortByNameDescending();
                break;
            case 3:
                cart.sortByTotalPriceAscending();
                break;
            case 4:
                cart.sortByTotalPriceDescending();
                break;
            case 5:
                cart.sortByQtyAscending();
                break;
            case 6:
                cart.sortByQtyDescending();
                break;
            case 7:
                cart.sortByQtyPriceAscending();
                break;
            case 8:
                cart.sortByQtyPriceDescending();
                break;
        }
        System.out.println(Font.TEXT_YELLOW);
        System.out.printf("%67s","Press enter key to go back Cart menu");
        pressAnyKeyToContinue();
        cartMenu(productArrayList,cart,customer);
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~TODO: End Cart Menu~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~TODO: Product Menu ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public static void productMenu(ArrayList<Product> productArrayList,Cart cart, Customer customer) throws InterruptedException {
        //Display product menu
        clearScreen();
        System.out.print(Font.TEXT_CYAN);
        System.out.printf(  "%59s", "+---------------+");
        System.out.printf("%n%59s", "|    Product    |");
        System.out.printf("%n%59s", "+---------------+");
        System.out.print(Font.RESET);
        System.out.println();
        System.out.printf(  "%64s", "[1] Search product        ");
        System.out.printf("%n%64s", "[2] Category              ");
        System.out.printf("%n%64s", "[3] Back to profile Menu  ");
        System.out.printf("%n%64s", "[4] Back to Cart Menu     ");
        // Get input for cart option
        boolean cartOptionError = true;
        int cartOption = 0;
        do {
            try {
                System.out.print(Font.TEXT_BLUE);
                System.out.printf("%n%51s", "Enter your option: ");
                cartOption = input.nextInt();
                Validation.validOption(cartOption, 1, 4);
                cartOptionError = false;
            } catch (ValidationException e) {
                System.err.println(e.getMessage());
                Thread.sleep(1000);
            } catch (InputMismatchException e) {
                System.err.println(Font.useFont(Font.BOLD_RED, "Please only key in integer"));
                input.nextLine();
                Thread.sleep(1000);
            }
        } while (cartOptionError);
        clearScreen();
        switch (cartOption) {
            case 1:
                searchProductMenu(productArrayList, cart, customer);
                break;
            case 2:
                categoryMenu(productArrayList, cart, customer);
                break;
            case 3:
                custMenu(adminArrList, customerArrList, customer);
                break;
            case 4:
                cartMenu(productArrayList,cart,customer);
                break;
        }

    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~TODO: Start Search~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //***************TODO: Different Search Option***************//
    public static void searchProductMenu(ArrayList<Product> productArrayList, Cart cart,Customer customer) throws InterruptedException {
        clearScreen();
        System.out.print(Font.TEXT_CYAN);
        System.out.printf(  "%66s", "+----------------------+");
        System.out.printf("%n%66s", "|    Search Product    |");
        System.out.printf("%n%66s", "+----------------------+");
        System.out.print(Font.RESET);
        System.out.printf("%n%71s", "[1] Product ID            ");
        System.out.printf("%n%71s", "[2] Product Name          ");
        System.out.printf("%n%71s", "[3] Price Range           ");
        System.out.printf("%n%71s", "[4] Back to previous      ");
        System.out.println();
        // Get input for cart option
        boolean searchOptionError = true;
        int searchOption = 0;
        do {
            try {
                System.out.print(Font.TEXT_BLUE);
                System.out.printf("%n%58s", "Enter your option: ");
                searchOption = input.nextInt();
                Validation.validOption(searchOption, 1, 4);
                searchOptionError = false;
            } catch (ValidationException e) {
                System.err.println(e.getMessage());
                Thread.sleep(1000);
            } catch (InputMismatchException e) {
                // Customize the message for let user know
                System.err.println(Font.useFont(Font.BOLD_RED, "Please only key in integer"));
                input.nextLine();
                Thread.sleep(1000);
            }
        } while (searchOptionError);
        switch (searchOption) {
            case 1: //Search by product ID
                searchProductByProductID(productArrayList, cart,customer);
                break;
            case 2: //Search by name
                searchProductByProductName(productArrayList, cart,customer);
                break;
            case 3: //Search by price range
                searchProductByPriceRange(productArrayList, cart, customer);
                break;
            case 4: //Back to previous menu
                productMenu(productArrayList,cart,customer);
                break;
        }

    }

    private static void searchProductByProductID(ArrayList<Product> productArrayList, Cart cart, Customer customer) throws InterruptedException {
        clearScreen();
        System.out.print(Font.TEXT_YELLOW);
        System.out.printf("%n%68s", "T For Shirt               ");
        System.out.printf("%n%68s", "P For Pant                ");
        System.out.printf("%n%68s", "A For Accessories         ");
        System.out.printf("%n%68s", "S For Shoe                ");
        System.out.printf("%n%83s", "Format: T/P/A/S + Product ID             ");
        System.out.print(Font.RESET);
        input.nextLine();
        System.out.print(Font.TEXT_BLUE);
        System.out.printf("%n%75s","Enter the ID that you want to search: ");
        System.out.print(Font.RESET);
        String targetProductID = input.nextLine().toUpperCase();
        char filterProductID = targetProductID.charAt(0);
        while (!Validation.validProductID(filterProductID)) {
            System.out.print(Font.TEXT_BLUE);
            System.out.printf("%n%75s","Enter the ID that you want to search: ");
            System.out.print(Font.RESET);
            targetProductID = input.nextLine().toUpperCase();
            filterProductID = targetProductID.charAt(0);
        }
        //Perform fast linear search with filter id
        Product targetProduct = linearSearchByProductID(productArrayList, targetProductID, filterProductID);
        if (targetProduct == null) {
            System.out.print(Font.TEXT_RED);
            System.out.printf("%n%64s", "Product ID not found");
            System.out.print(Font.RESET);
            boolean searchAgainError = true;
            char searchAgain = 'A';
            do {
                try {
                    System.out.print(Font.TEXT_BLUE);
                    System.out.printf("%n%72s", "Do you want to search again? (Y/N) ");
                    searchAgain = Character.toUpperCase(input.next().charAt(0));
                    Validation.validCharYN(searchAgain);
                    searchAgainError = false;
                } catch (ValidationException e) {
                    System.err.println(e.getMessage());
                    input.nextLine();
                    Thread.sleep(1000);
                }
            } while (searchAgainError);
            if (searchAgain == 'Y') {
                searchProductByProductID(productArrayList, cart,customer);
            } else {
                searchProductMenu(productArrayList, cart,customer);
            }
        } else {
            System.out.println(Font.TEXT_YELLOW);
            System.out.println(targetProduct);
            System.out.println(Font.RESET);
            //valid user choices
            boolean addToCartError = true;
            char addToCart = 'A';
            do {
                try {
                    System.out.print("Do you want to add this product to your cart? (Y/N) ");
                    addToCart = Character.toUpperCase(input.next().charAt(0));
                    Validation.validCharYN(addToCart);
                    addToCartError = false;
                } catch (ValidationException e) {
                    System.err.println(e.getMessage());
                    input.nextLine();
                    Thread.sleep(1000);
                }
            } while (addToCartError);
            if (addToCart == 'Y') {
                boolean quantityOptionError = true;
                int quantityOption = 0;
                do {
                    try {
                        System.out.print("Enter the quantity of this product that you want to add to your cart: ");
                        quantityOption = input.nextInt();
                        Validation.validProductQuantity(quantityOption, targetProduct.getQty());
                        quantityOptionError = false;
                    } catch (ValidationException e) {
                        System.err.println(e.getMessage());
                        Thread.sleep(1000);
                    } catch (InputMismatchException e) {
                        System.err.println(Font.useFont(Font.BOLD_RED, "Please only key in integer"));
                        input.nextLine();
                        Thread.sleep(1000);
                    }
                } while (quantityOptionError);
                cart.addItem(targetProduct.clone(), quantityOption);
                boolean searchAgainError = true;
                char searchAgain = 'A';
                do {
                    try {
                        System.out.print("Do you want to search again? (Y/N) ");
                        searchAgain = Character.toUpperCase(input.next().charAt(0));
                        Validation.validCharYN(searchAgain);
                        searchAgainError = false;
                    } catch (ValidationException e) {
                        System.err.println(e.getMessage());
                        input.nextLine();
                        Thread.sleep(1000);
                    }
                } while (searchAgainError);
                if (searchAgain == 'Y') {
                    searchProductByProductID(productArrayList, cart,customer);
                } else {
                    searchProductMenu(productArrayList, cart,customer);
                }
            } else {
                boolean searchAgainError = true;
                char searchAgain = 'A';
                do {
                    try {
                        System.out.print("Do you want to search again? (Y/N) ");
                        searchAgain = Character.toUpperCase(input.next().charAt(0));
                        Validation.validCharYN(searchAgain);
                        searchAgainError = false;
                    } catch (ValidationException e) {
                        System.err.println(e.getMessage());
                        input.nextLine();
                        Thread.sleep(1000);
                    }
                } while (searchAgainError);
                if (searchAgain == 'Y') {
                    searchProductByProductID(productArrayList, cart,customer);
                } else {
                    searchProductMenu(productArrayList, cart,customer);
                }
            }

        }

    }

    private static void searchProductByProductName(ArrayList<Product> productArrayList, Cart cart, Customer customer) throws InterruptedException {
        clearScreen();
        input.nextLine();
        System.out.print("Enter the name that you want to search: ");
        String targetProductName = input.nextLine().toUpperCase();
        ArrayList<Product> searchResultList = linearSearchByProductName(productArrayList, targetProductName);
        if (searchResultList.size() == 0) {
            System.out.println("Product not found");
            boolean searchAgainError = true;
            char searchAgain = 'A';
            do {
                try {
                    System.out.print("Do you want to search again? (Y/N) ");
                    searchAgain = Character.toUpperCase(input.next().charAt(0));
                    Validation.validCharYN(searchAgain);
                    searchAgainError = false;
                } catch (ValidationException e) {
                    System.err.println(e.getMessage());
                    input.nextLine();
                    Thread.sleep(1000);
                }
            } while (searchAgainError);
            if (searchAgain == 'Y') {
                searchProductByProductName(productArrayList, cart,customer);
            } else {
                searchProductMenu(productArrayList, cart,customer);
            }
        } else {
            for (Product product : searchResultList) {
                System.out.println(product);
            }
            boolean addToCartError = true;
            char addToCart = 'A';
            do {
                try {
                    System.out.print("Do you want to add product to your cart? (Y/N) ");
                    addToCart = Character.toUpperCase(input.next().charAt(0));
                    Validation.validCharYN(addToCart);
                    addToCartError = false;
                } catch (ValidationException e) {
                    System.err.println(e.getMessage());
                    input.nextLine();
                    Thread.sleep(1000);
                }
            } while (addToCartError);
            if (addToCart == 'Y') {
                input.nextLine();
                System.out.print("Enter the product ID that you want to add to cart: ");
                String targetProductID = input.nextLine().toUpperCase();
                char filterProductID = targetProductID.charAt(0);
                Product targetProduct = linearSearchByProductID(searchResultList, targetProductID, filterProductID);
                while (targetProduct == null) {
                    System.out.println("Product not found");
                    System.out.print("Please reenter the product ID that you want to add to cart: ");
                    targetProductID = input.nextLine().toUpperCase();
                    filterProductID = targetProductID.charAt(0);
                    targetProduct = linearSearchByProductID(searchResultList, targetProductID, filterProductID);
                }
                boolean quantityOptionError = true;
                int quantityOption = 0;
                do {
                    try {
                        System.out.print("Enter the quantity of this product that you want to add to your cart: ");
                        quantityOption = input.nextInt();
                        Validation.validProductQuantity(quantityOption, targetProduct.getQty());
                        quantityOptionError = false;
                    } catch (ValidationException e) {
                        System.err.println(e.getMessage());
                        Thread.sleep(1000);
                    } catch (InputMismatchException e) {
                        System.err.println(Font.useFont(Font.BOLD_RED, "Please only key in integer"));
                        input.nextLine();
                        Thread.sleep(1000);
                    }
                } while (quantityOptionError);
                cart.addItem(targetProduct.clone(), quantityOption);
                boolean searchAgainError = true;
                char searchAgain = 'A';
                do {
                    try {
                        System.out.print("Do you want to search again? (Y/N) ");
                        searchAgain = Character.toUpperCase(input.next().charAt(0));
                        Validation.validCharYN(searchAgain);
                        searchAgainError = false;
                    } catch (ValidationException e) {
                        System.err.println(e.getMessage());
                        input.nextLine();
                        Thread.sleep(1000);
                    }
                } while (searchAgainError);
                if (searchAgain == 'Y') {
                    searchProductByProductName(productArrayList, cart,customer);
                } else {
                    searchProductMenu(productArrayList, cart,customer);
                }
            } else {
                boolean searchAgainError = true;
                char searchAgain = 'A';
                do {
                    try {
                        System.out.print("Do you want to search again? (Y/N) ");
                        searchAgain = Character.toUpperCase(input.next().charAt(0));
                        Validation.validCharYN(searchAgain);
                        searchAgainError = false;
                    } catch (ValidationException e) {
                        System.err.println(e.getMessage());
                        input.nextLine();
                        Thread.sleep(1000);
                    }
                } while (searchAgainError);
                if (searchAgain == 'Y') {
                    searchProductByProductName(productArrayList, cart,customer);
                } else {
                    searchProductMenu(productArrayList, cart,customer);
                }
            }
        }
    }

    private static void searchProductByPriceRange(ArrayList<Product> productArrayList, Cart cart,Customer customer) throws InterruptedException {
        clearScreen();
        input.nextLine();
        boolean priceRangeError = true;
        int lowerPrice = 0;
        int upperPrice = 0;
        do {
            try {
                System.out.print("Enter the lower price that you want to search for: RM");
                lowerPrice = input.nextInt();
                System.out.print("Enter the upper price that you want to search for: RM");
                upperPrice = input.nextInt();
                Validation.validPriceRange(lowerPrice, upperPrice);
                priceRangeError = false;
            } catch (ValidationException e) {
                System.err.println(e.getMessage());
                Thread.sleep(1000);
            } catch (InputMismatchException e) {
                System.err.println(Font.useFont(Font.BOLD_RED, "Please only key in integer"));
                input.nextLine();
                Thread.sleep(1000);
            }
        } while (priceRangeError);
        ArrayList<Product> searchResultList = linearSearchByPriceRange(productArrayList, lowerPrice, upperPrice);
        if (searchResultList.size() == 0) {
            System.out.println("Product not found");
            boolean searchAgainError = true;
            char searchAgain = 'A';
            do {
                try {
                    System.out.print("Do you want to search again? (Y/N) ");
                    searchAgain = Character.toUpperCase(input.next().charAt(0));
                    Validation.validCharYN(searchAgain);
                    searchAgainError = false;
                } catch (ValidationException e) {
                    System.err.println(e.getMessage());
                    input.nextLine();
                    Thread.sleep(1000);
                }
            } while (searchAgainError);
            if (searchAgain == 'Y') {
                searchProductByPriceRange(productArrayList, cart,customer);
            } else {
                searchProductMenu(productArrayList, cart,customer);
            }
        } else {
            for (Product product : searchResultList) {
                System.out.println(product);
            }
            boolean addToCartError = true;
            char addToCart = 'A';
            do {
                try {
                    System.out.print("Do you want to add product to your cart? (Y/N) ");
                    addToCart = Character.toUpperCase(input.next().charAt(0));
                    Validation.validCharYN(addToCart);
                    addToCartError = false;
                } catch (ValidationException e) {
                    System.err.println(e.getMessage());
                    input.nextLine();
                    Thread.sleep(1000);
                }
            } while (addToCartError);
            if (addToCart == 'Y') {
                input.nextLine();
                System.out.print("Enter the product ID that you want to add to cart: ");
                String targetProductID = input.nextLine().toUpperCase();
                char filterProductID = targetProductID.charAt(0);
                Product targetProduct = linearSearchByProductID(searchResultList, targetProductID, filterProductID);
                while (targetProduct == null) {
                    System.out.println("Product not found");
                    System.out.print("Please reenter the product ID that you want to add to cart: ");
                    targetProductID = input.nextLine().toUpperCase();
                    filterProductID = targetProductID.charAt(0);
                    targetProduct = linearSearchByProductID(searchResultList, targetProductID, filterProductID);
                }
                boolean quantityOptionError = true;
                int quantityOption = 0;
                do {
                    try {
                        System.out.print("Enter the quantity of this product that you want to add to your cart: ");
                        quantityOption = input.nextInt();
                        Validation.validProductQuantity(quantityOption, targetProduct.getQty());
                        quantityOptionError = false;
                    } catch (ValidationException e) {
                        System.err.println(e.getMessage());
                        Thread.sleep(1000);
                    } catch (InputMismatchException e) {
                        System.err.println(Font.useFont(Font.BOLD_RED, "Please only key in integer"));
                        input.nextLine();
                        Thread.sleep(1000);
                    }
                } while (quantityOptionError);
                cart.addItem(targetProduct.clone(), quantityOption);
                boolean searchAgainError = true;
                char searchAgain = 'A';
                do {
                    try {
                        System.out.print("Do you want to search again? (Y/N) ");
                        searchAgain = Character.toUpperCase(input.next().charAt(0));
                        Validation.validCharYN(searchAgain);
                        searchAgainError = false;
                    } catch (ValidationException e) {
                        System.err.println(e.getMessage());
                        input.nextLine();
                        Thread.sleep(1000);
                    }
                } while (searchAgainError);
                if (searchAgain == 'Y') {
                    searchProductByPriceRange(productArrayList, cart,customer);
                } else {
                    searchProductMenu(productArrayList, cart,customer);
                }
            } else {
                boolean searchAgainError = true;
                char searchAgain = 'A';
                do {
                    try {
                        System.out.print("Do you want to search again? (Y/N) ");
                        searchAgain = Character.toUpperCase(input.next().charAt(0));
                        Validation.validCharYN(searchAgain);
                        searchAgainError = false;
                    } catch (ValidationException e) {
                        System.err.println(e.getMessage());
                        input.nextLine();
                        Thread.sleep(1000);
                    }
                } while (searchAgainError);
                if (searchAgain == 'Y') {
                    searchProductByPriceRange(productArrayList, cart, customer);
                } else {
                    searchProductMenu(productArrayList, cart,customer);
                }
            }
        }

    }

    //***************TODO: Linear Search Algorithm***************//
    public static Product linearSearchByProductID(ArrayList<Product> productArrayList, String targetProductID, char filterProductID) {
        Product targetProduct = null;

        for (Product product : productArrayList) {
            if (filterProductID == 'T' && product instanceof Shirt) {
                if (Objects.equals(((Shirt) product).getShirtID(), targetProductID)) {
                    targetProduct = product;
                    break;
                }
            } else if (filterProductID == 'P' && product instanceof Pant) {
                if (Objects.equals(((Pant) product).getPantID(), targetProductID)) {
                    targetProduct = product;
                    break;
                }

            } else if (filterProductID == 'A' && product instanceof Accessories) {
                if (Objects.equals(((Accessories) product).getAccessoriesID(), targetProductID)) {
                    targetProduct = product;
                    break;
                }
            } else if (filterProductID == 'S' && product instanceof Shoe) {
                if (Objects.equals(((Shoe) product).getShoeID(), targetProductID)) {
                    targetProduct = product;
                    break;
                }
            }
        }
        return targetProduct;
    }

    private static ArrayList<Product> linearSearchByProductName(ArrayList<Product> productArrayList, String targetProductName) {
        ArrayList<Product> searchResultList = new ArrayList<>();
        for (Product product : productArrayList) {
            if (Objects.equals(product.getName().toUpperCase(), targetProductName) || product.getName().toUpperCase().contains(targetProductName)) {
                searchResultList.add(product);
            }
        }
        return searchResultList;
    }

    private static Product linearSearchByCartItemName(ArrayList<Product> productArrayList, String targetCartItemName) {
        for (Product product : productArrayList) {
            if (Objects.equals(product.getName().toUpperCase(), targetCartItemName.toUpperCase())){
                return product;
            }
        }
        return null;
    }

    private static ArrayList<Product> linearSearchByPriceRange(ArrayList<Product> productArrayList, double lowerPrice, double upperPrice) {
        ArrayList<Product> searchResultList = new ArrayList<>();
        for (Product product : productArrayList) {
            if (product.getPrice() >= lowerPrice && product.getPrice() <= upperPrice) {
                searchResultList.add(product);
            }
        }
        return searchResultList;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~TODO: End Search~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~TODO: Start Category & Best Sales~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public static void categoryMenu(ArrayList<Product> productArrayList, Cart cart, Customer customer) throws InterruptedException {
        clearScreen();
        System.out.println("\t\t+------------+");
        System.out.println("\t\t|  Category  |");
        System.out.println("\t\t+------------+");
        System.out.println("[1] Shirt");
        System.out.println("[2] Pant");
        System.out.println("[3] Accessories");
        System.out.println("[4] Shoes");
        System.out.println("[5] Best Sales");
        System.out.println("[6] Back to previous Menu");

        // Get input for cart option
        boolean categoryOptionError = true;
        int categoryOption = 0;
        do {
            try {
                System.out.print("\nEnter your option: ");
                categoryOption = input.nextInt();
                Validation.validOption(categoryOption, 1, 6);
                categoryOptionError = false;
            } catch (ValidationException e) {
                System.err.println(e.getMessage());
                Thread.sleep(1000);
            } catch (InputMismatchException e) {
                // Customize the message for let user know
                System.err.println(Font.useFont(Font.BOLD_RED, "Please only key in integer"));
                input.nextLine();
                Thread.sleep(1000);
            }
        } while (categoryOptionError);

        //Display which category
        clearScreen();
        switch (categoryOption) {
            case 1:
                Category(productArrayList, cart,"Shirt",customer);
                break;
            case 2:
                Category(productArrayList, cart,"Pant",customer);
                break;
            case 3:
                Category(productArrayList, cart,"Accessories",customer);
                break;
            case 4:
                Category(productArrayList, cart,"Shoe",customer);
                break;
            case 5:
                Category(productArrayList, cart,"Best Sales",customer);
                break;
            case 6:
                productMenu(productArrayList,cart,customer);
                break;
        }
    }

    private static void Category(ArrayList<Product> productArrayList, Cart cart, String category, Customer customer) throws InterruptedException {
        //Display product shirt
        ArrayList<Product> categoryResultList = linearSearchByProductCategory(productArrayList, category);
        for (Product product : categoryResultList) {
            System.out.println(product);
        }
        boolean addToCartError = true;
        char addToCart = 'A';
        do {
            try {
                System.out.print("Do you want to add product to your cart? (Y/N) ");
                addToCart = Character.toUpperCase(input.next().charAt(0));
                Validation.validCharYN(addToCart);
                addToCartError = false;
            } catch (ValidationException e) {
                System.err.println(e.getMessage());
                input.nextLine();
                Thread.sleep(1000);
            }
        } while (addToCartError);
        if (addToCart == 'Y') {
            input.nextLine();
            System.out.print("Enter the product ID that you want to add to cart: ");
            String targetProductID = input.nextLine().toUpperCase();
            char filterProductID = targetProductID.charAt(0);
            Product targetProduct = linearSearchByProductID(categoryResultList, targetProductID, filterProductID);
            while (targetProduct == null) {
                System.out.println("Product not found");
                System.out.print("Please reenter the product ID that you want to add to cart: ");
                targetProductID = input.nextLine().toUpperCase();
                filterProductID = targetProductID.charAt(0);
                targetProduct = linearSearchByProductID(categoryResultList, targetProductID, filterProductID);
            }
            boolean quantityOptionError = true;
            int quantityOption = 0;
            do {
                try {
                    System.out.print("Enter the quantity of this product that you want to add to your cart: ");
                    quantityOption = input.nextInt();
                    Validation.validProductQuantity(quantityOption, targetProduct.getQty());
                    quantityOptionError = false;
                } catch (ValidationException e) {
                    System.err.println(e.getMessage());
                    Thread.sleep(1000);
                } catch (InputMismatchException e) {
                    System.err.println(Font.useFont(Font.BOLD_RED, "Please only key in integer"));
                    input.nextLine();
                    Thread.sleep(1000);
                }
            } while (quantityOptionError);
            cart.addItem(targetProduct.clone(), quantityOption);
            System.out.println("Press enter key to go back category menu");
            pressAnyKeyToContinue();
            categoryMenu(productArrayList, cart,customer);
        } else{
            System.out.println("Press enter key to go back category menu");
            pressAnyKeyToContinue();
            categoryMenu(productArrayList, cart,customer);
        }
    }

    private static ArrayList<Product> linearSearchByProductCategory(ArrayList<Product> productArrayList, String category) {
        ArrayList<Product> categoryResultList = new ArrayList<>();
        switch (category) {
            case "Shirt":
                for (Product product : productArrayList) {
                    if(product instanceof Shirt) {
                        categoryResultList.add(product);
                    }
                }
                break;
            case "Pant":
                for (Product product : productArrayList) {
                    if (product instanceof Pant) {
                        categoryResultList.add(product);
                    }
                }
                break;
            case "Shoe":
                for (Product product : productArrayList) {
                    if (product instanceof Shoe) {
                        categoryResultList.add(product);
                    }
                }
                break;
            case "Accessories":
                for (Product product : productArrayList) {
                    if (product instanceof Accessories) {
                        categoryResultList.add(product);
                    }
                }
                break;
            case "Best Sales":
                for (Product product : productArrayList) {
                    if (product.getRating() == 5) {
                        categoryResultList.add(product);
                    }
                }
        }

        return categoryResultList;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~TODO: End Category & Best Sales~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~TODO: End Product Menu ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    //TODO: Payment + Order @Author EUGENE LAW KAI LE
    public static void paymentMenu(Cart cart, ArrayList<Payment> payment, Customer customer) throws InterruptedException {
        clearScreen();
        System.out.print(Font.TEXT_CYAN);
        System.out.printf("%55s",  "+-------------+");
        System.out.printf("%n%55s","|   Payment   |");
        System.out.printf("%n%55s","+-------------+");
        System.out.print(Font.RESET);
        System.out.println(Font.TEXT_BRIGHT_MAGENTA);
        System.out.printf("%59s","Choose A Payment Method");
        System.out.print(Font.RESET);
        System.out.printf("%n%61s","[1] Bank             ");
        System.out.printf("%n%61s","[2] E-Wallet         ");
        System.out.printf("%n%61s","[3] Debit/Credit     ");
        System.out.printf("%n%61s","[4] Back to cart Menu");

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
                Validation.validOption(paymentOption, 1, 4);
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
                bank(bank1, cart,customer);
            }
            case 2 -> {
                Payment eWallet1 = new EWallet();
                eWallet(eWallet1, cart,customer);
            }
            case 3 -> {
                Payment debitCredit1 = new DebitCredit();
                debitCredit(debitCredit1, cart,customer);
            }
            case 4 -> {
                cartMenu(productArrayList, cart, customer);
            }
        }
    }

    // Receive Bank details
    public static void bank(Payment bank, Cart cart,Customer customer) throws InterruptedException {
        clearScreen();
        bank.setTotalPrice(cart.getTotalPrice());
        System.out.println(Font.TEXT_BRIGHT_MAGENTA);
        System.out.printf("%67s","You have chose Bank as payment method");
        System.out.println(Font.RESET);
        System.out.printf("%59s","Total Price (RM): " + df2.format(cart.getTotalPrice()));

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
                Thread.sleep(1000);
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
        ((Bank) bank).setPayAmount(payAmount);
        clearScreen();
        System.out.println(bank.toString());
        payment.add(bank.clone());
        System.out.println("\nThank you for shopping!");

        System.out.println("Press enter key to return to main menu");
        pressAnyKeyToContinue();
        setOrder(customer,cart,bank);
    }

    // Receive E-Wallet details
    public static void eWallet(Payment eWallet, Cart cart,Customer customer) throws InterruptedException {
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
        ((EWallet) eWallet).setPayAmount(payAmount);
        payment.add(eWallet.clone());
        clearScreen();
        System.out.println(eWallet);
        System.out.println("\nThank you for shopping!");
        System.out.println("Press enter key to return to main menu");
        pressAnyKeyToContinue();
        setOrder(customer,cart,eWallet);
    }

    // Receive Debit/Credit details
    public static void debitCredit(Payment debitCredit, Cart cart, Customer customer) throws InterruptedException {
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
        ((DebitCredit) debitCredit).setPayAmount(payAmount);
        payment.add(debitCredit.clone());
        clearScreen();
        System.out.println(debitCredit);
        System.out.println("\nThank you for shopping!");
        System.out.println("Press enter key to return to main menu");
        pressAnyKeyToContinue();
        setOrder(customer,cart,debitCredit);
    }

    //Prompt delivery
    public static void setOrder(Customer customer, Cart cart, Payment payment) throws InterruptedException {
        Order order = new Order(customer.clone(),cart.clone(),payment.clone());
        ArrayList<Product> copyList;
        copyList = (ArrayList<Product>) cart.getCartItem().clone();
        order.setOrderList(copyList);

        customer.addOrder(order);
        cart.reduceStock(productArrayList);
        cart.clearCart();
        custMenu(adminArrList,customerArrList,customer);
    }

    public static void orderHistory(Customer customer) throws InterruptedException {
        clearScreen();
        System.out.println(Font.TEXT_CYAN);
        System.out.printf("%55s","+------------+");
        System.out.printf("%n%55s","|   Orders   |");
        System.out.printf("%n%55s","+------------+");
        System.out.println(Font.RESET);

        customer.displayOrder();

        System.out.printf("%61s","[1] View order details");
        System.out.printf("%n%62s","[2] Return to main menu");

        // Get input for order option
        boolean orderOptionError = true;
        int orderOption = 0;
        do {
            try {
                System.out.print(Font.TEXT_BLUE);
                System.out.printf("%n%54s",  "Enter your option: ");
                orderOption = input.nextInt();
                System.out.print(Font.RESET);
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

        switch (orderOption) {
            case 1:
                viewOrderDetail(customer);
                break;
            case 2:
                custMenu(adminArrList,customerArrList,customer);
                break;
        }
    }

    public static void viewOrderDetail(Customer customer) throws InterruptedException {
        boolean orderIndexError = true;
        int orderIndex = 0;
        do {
            try {
                System.out.print(Font.TEXT_BLUE);
                System.out.printf("%n%54s",  "Enter the order index: ");
                orderIndex = input.nextInt();
                Validation.validOption(orderIndex, 1, customer.getOrderHistory().size());
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
        clearScreen();
        customer.viewOrder(orderIndex);
        System.out.println("Press enter key to return to order history");
        pressAnyKeyToContinue();
        orderHistory(customer);
    }

    //TODO: TEAM Tools @Author Team
    public static void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public static void pressAnyKeyToContinue(){
        input.nextLine();
        input.nextLine();
    }

    public static void pressAnyKeyToContinueWithPrompt(){
        System.out.println(Font.TEXT_BRIGHT_MAGENTA + "Press any key to continue..." + Font.RESET);
        input.nextLine();
    }


    public static void endProgram(){

        storeArrayList();
        System.out.println(Font.TEXT_CYAN);
        System.out.println(" /$$$$$$$$ /$$   /$$  /$$$$$$  /$$   /$$ /$$   /$$       /$$     /$$ /$$$$$$  /$$   /$$               ");
        System.out.println("|__  $$__/| $$  | $$ /$$__  $$| $$$ | $$| $$  /$$/     |  $$   /$$//$$__  $$| $$  | $$               ");
        System.out.println("   | $$   | $$  | $$| $$  \\ $$| $$$$| $$| $$ /$$/      \\  $$ /$$/| $$    \\ $$| $$  | $$      ");
        System.out.println("   | $$   | $$$$$$$$| $$$$$$$$| $$ $$ $$| $$$$$/         \\  $$$$/ | $$  | $$| $$  | $$            ");
        System.out.println("   | $$   | $$__  $$| $$__  $$| $$  $$$$| $$  $$           \\  $$/  |$$  | $$| $$  | $$            ");
        System.out.println("   | $$   | $$  | $$| $$  | $$| $$ \\ $$$| $$ \\ $$           | $$   |$$  | $$| $$  | $$         ");
        System.out.println("   | $$   | $$  | $$| $$  | $$| $$  \\ $$| $$  \\ $$          | $$   |$$$$$$/|  $$$$$$/         ");
        System.out.println("   |__/   |__/  |__/|__/  |__/|__/   \\__/|__/  \\__/          |__/   \\______/    \\______/    ");
        System.out.println("                             Wish you have a nice day                                                         ");
        System.out.println("                                                                                                      ");

        System.exit(0);

    }




    }







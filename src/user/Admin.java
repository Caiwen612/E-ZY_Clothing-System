package user;


import product.Accessories;
import product.Pant;
import product.Shirt;
import product.Shoe;
import utility.Font;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static driver.DriverProgram.adminMenu;

public class Admin extends People {
    private static String doubleAuthCode;
    private static int staffCount = 0;
    private String staffID;


    public Admin() {
        this("", "", "", "");
        staffCount++;
    }

    public Admin(String name, String email, String password, String phoneNo) {
        super(name, email, password, phoneNo);
        staffCount++;
    }

    // getter and setter
    public static String getDoubleAuthCode() {
        return doubleAuthCode;
    }

    public static void setDoubleAuthCode(String doubleAuthCode) {
        Admin.doubleAuthCode = doubleAuthCode;
    }

    public static int getStaffCount() {
        return staffCount;
    }

    public static void setStaffCount(int staffCount) {
        Admin.staffCount = staffCount;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    // methods
    public void generateStaffID() {
        this.setStaffID("S" + staffCount);
    }

    public boolean vldDoubleAuth(String code) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("authCode.txt"));
            doubleAuthCode = reader.readLine();
            reader.close();
        } catch (IOException e) {
            // pinpoint the exact line in which the method raised the exception
            e.printStackTrace();
        }

        if (code.equals(doubleAuthCode)) {
            return true;
        }
        else {
            return false;
        }
    }

    // modify, add, edit products

    public void modifyProduct(Object obj, ArrayList<Admin> adminArrList, ArrayList<Customer> customerArrList, Admin admin) throws InterruptedException {
        int option = 0;
        boolean optionVld = true;
        Scanner scanner = new Scanner(System.in);

        do {

            utility.utility.clearScreen();
            System.out.print(Font.TEXT_CYAN);
            System.out.printf(  "%55s", "+-----------------------+");
            System.out.printf("%n%55s", "|    Modify Products    |");
            System.out.printf("%n%55s%n", "+----------------------+");
            System.out.print(Font.RESET);

            System.out.println("What do you wish to modify?");
            System.out.println("1. product.Product Name");
            System.out.println("2. Price");
            System.out.println("3. Quantity");
            System.out.println("4. Back to Previous Page");
            System.out.println("5. Exit");

            System.out.println();
            System.out.print("Selection: ");
            try {
                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        if (obj instanceof Accessories) {
                            System.out.println();
                            System.out.println("Original Name: " + Font.BOLD_YELLOW + ((Accessories) obj).getName());
                            System.out.print(Font.RESET);
                            System.out.print("New Name: ");
                            ((Accessories) obj).setName(scanner.nextLine());
                        }
                        else if (obj instanceof Pant) {
                            System.out.println();
                            System.out.println("Original Name: " + Font.BOLD_YELLOW + ((Pant) obj).getName());
                            System.out.print(Font.RESET);
                            System.out.print("New Name: ");
                            ((Pant) obj).setName(scanner.nextLine());

                        }
                        else if (obj instanceof Shirt) {
                            System.out.println();
                            System.out.println("Original Name: " + Font.BOLD_YELLOW + ((Shirt) obj).getName());
                            System.out.print(Font.RESET);
                            System.out.print("New Name: ");
                            ((Shirt) obj).setName(scanner.nextLine());

                        }
                        else if (obj instanceof Shoe) {
                            System.out.println();
                            System.out.println("Original Name: " + Font.BOLD_YELLOW + ((Shoe) obj).getName());
                            System.out.print(Font.RESET);
                            System.out.print("New Name: ");
                            ((Shoe) obj).setName(scanner.nextLine());

                        }
                        else {
                            System.out.println(Font.BOLD_RED + "No product.Product Found.");
                            System.out.print(Font.RESET);
                        }
                        break;

                    case 2:
                        if (obj instanceof Accessories) {
                            System.out.println();
                            System.out.println("Original Price: " + Font.BOLD_YELLOW + ((Accessories) obj).getPrice());
                            System.out.print(Font.RESET);
                            System.out.print("New Price: RM");
                            ((Accessories) obj).setPrice(scanner.nextDouble());
                            scanner.nextLine();
                        }
                        else if (obj instanceof Pant) {
                            System.out.println();
                            System.out.println("Original Price: " + Font.BOLD_YELLOW + ((Pant) obj).getPrice());
                            System.out.print(Font.RESET);
                            System.out.print("New Price: RM");
                            ((Pant) obj).setPrice(scanner.nextDouble());
                            scanner.nextLine();
                        }
                        else if (obj instanceof Shirt) {
                            System.out.println();
                            System.out.println("Original Price: " + Font.BOLD_YELLOW + ((Shirt) obj).getPrice());
                            System.out.print(Font.RESET);
                            System.out.print("New Price: RM");
                            ((Shirt) obj).setPrice(scanner.nextDouble());
                            scanner.nextLine();
                        }
                        else if (obj instanceof Shoe) {
                            System.out.println();
                            System.out.println("Original Price: " + Font.BOLD_YELLOW + ((Shoe) obj).getPrice());
                            System.out.print(Font.RESET);
                            System.out.print("New Price: RM");
                            ((Shoe) obj).setPrice(scanner.nextDouble());
                            scanner.nextLine();
                        }
                        else {
                            System.out.println(Font.BOLD_RED + "No product.Product Found.");
                            System.out.print(Font.RESET);
                        }
                        break;
                    case 3:
                        if (obj instanceof Accessories) {
                            System.out.println();
                            System.out.println("Original Quantity: " + Font.BOLD_YELLOW + ((Accessories) obj).getQty());
                            System.out.print(Font.RESET);
                            System.out.print("New Quantity: ");
                            ((Accessories) obj).setQty(scanner.nextInt());
                            scanner.nextLine();
                        }
                        else if (obj instanceof Pant) {
                            System.out.println();
                            System.out.println("Original Price: " + Font.BOLD_YELLOW + ((Pant) obj).getPrice());
                            System.out.print(Font.RESET);
                            System.out.print("New Price: RM");
                            ((Pant) obj).setPrice(scanner.nextInt());
                            scanner.nextLine();
                        }
                        else if (obj instanceof Shirt) {
                            System.out.println();
                            System.out.println("Original Price: " + Font.BOLD_YELLOW + ((Shirt) obj).getPrice());
                            System.out.print(Font.RESET);
                            System.out.print("New Price: RM");
                            ((Shirt) obj).setPrice(scanner.nextInt());
                            scanner.nextLine();
                        }
                        else if (obj instanceof Shoe) {
                            System.out.println();
                            System.out.println("Original Price: " + Font.BOLD_YELLOW + ((Shoe) obj).getPrice());
                            System.out.print(Font.RESET);
                            System.out.print("New Price: RM");
                            ((Shoe) obj).setPrice(scanner.nextInt());
                            scanner.nextLine();
                        }
                        else {
                            System.out.println(Font.BOLD_RED + "No product.Product Found.");
                            System.out.print(Font.RESET);
                        }
                        break;
                    case 4:

                        adminMenu(adminArrList, customerArrList, admin);
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



    // report feature
}

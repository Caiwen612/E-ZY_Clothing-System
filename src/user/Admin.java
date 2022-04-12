package user;


import driver.DriverProgram;
import order.Order;
import product.*;
import utility.Font;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static driver.DriverProgram.*;


public class Admin extends People {
    private static String doubleAuthCode;
    private static int staffCount = 0;
    private String staffID;
    static DecimalFormat df2 = new DecimalFormat("0.00");


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
        this.setStaffID("ST" + staffCount);
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

    // modify products
    public void modifyProduct(Object obj, ArrayList<Admin> adminArrList, ArrayList<Customer> customerArrList, Admin admin) throws InterruptedException {
        int option = 0;
        boolean optionVld = true, productVld = true;
        Scanner scanner = new Scanner(System.in);

        do {
            productVld = true;
            clearScreen();
            System.out.print(Font.TEXT_CYAN);
            System.out.printf(  "%55s", "+---------------------+");
            System.out.printf("%n%55s", "|    Edit Products    |");
            System.out.printf("%n%55s%n", "+--------------------+");
            System.out.print(Font.RESET);

            System.out.println("What do you wish to modify?");
            System.out.println("1. Product Name");
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
                            productVld = false;
                            System.out.println(Font.BOLD_RED + "No Product Found.");
                            System.out.print(Font.RESET);
                        }
                        break;

                    case 2:
                        if (obj instanceof Accessories) {
                            System.out.println();
                            System.out.println("Original Price: " + Font.BOLD_YELLOW +  df2.format(((Accessories) obj).getPrice()));
                            System.out.print(Font.RESET);
                            System.out.print("New Price: RM");
                            ((Accessories) obj).setPrice(scanner.nextDouble());
                            scanner.nextLine();
                        }
                        else if (obj instanceof Pant) {
                            System.out.println();
                            System.out.println("Original Price: " + Font.BOLD_YELLOW + df2.format(((Pant) obj).getPrice()));
                            System.out.print(Font.RESET);
                            System.out.print("New Price: RM");
                            ((Pant) obj).setPrice(scanner.nextDouble());
                            scanner.nextLine();
                        }
                        else if (obj instanceof Shirt) {
                            System.out.println();
                            System.out.println("Original Price: " + Font.BOLD_YELLOW + df2.format(((Shirt) obj).getPrice()));
                            System.out.print(Font.RESET);
                            System.out.print("New Price: RM");
                            ((Shirt) obj).setPrice(scanner.nextDouble());
                            scanner.nextLine();
                        }
                        else if (obj instanceof Shoe) {
                            System.out.println();
                            System.out.println("Original Price: " + Font.BOLD_YELLOW + df2.format(((Shoe) obj).getPrice()));
                            System.out.print(Font.RESET);
                            System.out.print("New Price: RM");
                            ((Shoe) obj).setPrice(scanner.nextDouble());
                            scanner.nextLine();
                        }
                        else {
                            productVld = false;
                            System.out.println(Font.BOLD_RED + "No Product Found.");
                            System.out.print(Font.RESET);
                        }
                        break;
                    case 3:
                        if (obj instanceof Product) {
                            System.out.println();
                            System.out.println("Original Quantity: " + Font.BOLD_YELLOW + ((Product) obj).getQty());
                            System.out.print(Font.RESET);
                            System.out.print("New Quantity: ");
                            ((Product) obj).setQty(scanner.nextInt());
                            scanner.nextLine();
                        }
                        else {
                            productVld = false;
                            System.out.println(Font.BOLD_RED + "No Product Found.");
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
        } while (!optionVld && !productVld);

        adminMenu(adminArrList, customerArrList, admin);
    }

    // increment quantity
    public void addStock(Object obj, ArrayList<Admin> adminArrList, ArrayList<Customer> customerArrList, Admin admin) throws InterruptedException {
        boolean qtyVld = true, productVld = true;
        int qty;
        Scanner scanner = new Scanner(System.in);

        do {
            productVld = true;
            System.out.print("How Much Quantity Do You Want to Add: " + Font.TEXT_YELLOW);
            try {
                qty = scanner.nextInt();
                scanner.nextLine();

                if (qty <= 0) {
                    qtyVld = false;
                    System.out.println(Font.BOLD_RED + "Please Enter Only Positive Numbers." + Font.RESET);
                    Thread.sleep(1000);
                }
                else {
                    qtyVld = true;

                    if (obj instanceof Accessories) {
                        ((Accessories) obj).addQty(qty);
                    }
                    else if (obj instanceof Pant) {
                        ((Pant) obj).addQty(qty);
                    }
                    else if (obj instanceof Shirt) {
                        ((Shirt) obj).addQty(qty);
                    }
                    else if (obj instanceof Shoe) {
                        ((Shoe) obj).addQty(qty);
                    }
                    else {
                        productVld = false;
                        System.out.println(Font.BOLD_RED + "No Product Found.");
                        System.out.print(Font.RESET);
                    }

                }
            } catch (InputMismatchException inputMismatchException) {
                qtyVld = false;
                System.out.println(Font.BOLD_RED + "Please Enter Only Integer.");
                System.out.print(Font.RESET);
                scanner.nextLine();
                Thread.sleep(1000);
            }
        } while (!qtyVld && !productVld);

        adminMenu(adminArrList, customerArrList, admin);
    }

    // check quantity
    public static void checkQuantity(Object obj, ArrayList<Admin> adminArrList, ArrayList<Customer> customerArrList, Admin admin) throws InterruptedException {
        int qty;

        if (obj instanceof Accessories) {
            qty = ((Accessories) obj).getQty();
            System.out.println("Quantity: " + Font.TEXT_YELLOW + qty);
        }
        else if (obj instanceof Pant) {
            qty = ((Pant) obj).getQty();
            System.out.println("Quantity: " + Font.TEXT_YELLOW + qty);
        }
        else if (obj instanceof Shirt) {
            qty = ((Shirt) obj).getQty();
            System.out.println("Quantity: " + Font.TEXT_YELLOW + qty);
        }
        else if (obj instanceof Shoe) {
            qty = ((Shoe) obj).getQty();
            System.out.println("Quantity: " + Font.TEXT_YELLOW + qty);
        }
        else {
            System.out.println(Font.BOLD_RED + "No Product Found.");
            System.out.print(Font.RESET);
        }
        System.out.println("Press Enter to Continue.");
        DriverProgram.pressAnyKeyToContinueWithPrompt();
        adminMenu(adminArrList, customerArrList, admin);
    }

    // report
    public static void generateReport(ArrayList<Order> orderArrList, ArrayList<Admin> adminArrList, ArrayList<Customer> customerArrList, Admin admin) throws InterruptedException {
//        int index = 0;
        double totalPrice = 0;

        clearScreen();
        System.out.println(Font.TEXT_CYAN);
        System.out.printf("%40s","+------------+");
        System.out.printf("%n%40s","|   Report   |");
        System.out.printf("%n%40s","+------------+");
        System.out.println(Font.RESET);

        System.out.printf("%-10s | %-25s | %-14s | %-10s%n", "Product ID","Product Name", "Quantity Sold", "Price (RM)");
        for (int i = 0; i < 68; i++) {
            System.out.print("-");
        }
        System.out.println();
        for(int j = 0; j < orderArrList.size(); j++) {
            for (int k = 0; k < orderArrList.get(j).getOrderList().size(); k++) {
                    if(orderArrList.get(j).getOrderList().get(k) instanceof Pant){
                        System.out.printf("%-10s | %-25s | %-14s | %-10.2f%n",((((Pant) orderArrList.get(j).getOrderList().get(k)).getPantID())), orderArrList.get(j).getOrderList().get(k).getName(),orderArrList.get(j).getOrderList().get(k).getQty(),orderArrList.get(j).getOrderList().get(k).getTotalPrice());
                    }
                    else if(orderArrList.get(j).getOrderList().get(k) instanceof Accessories){
                        System.out.printf("%-10s | %-25s | %-14s | %-10.2f%n",((((Accessories) orderArrList.get(j).getOrderList().get(k)).getAccessoriesID())), orderArrList.get(j).getOrderList().get(k).getName(),orderArrList.get(j).getOrderList().get(k).getQty(),orderArrList.get(j).getOrderList().get(k).getTotalPrice());
                    } else if(orderArrList.get(j).getOrderList().get(k) instanceof Shirt){
                        System.out.printf("%-10s | %-25s | %-14s | %-10.2f%n",((((Shirt) orderArrList.get(j).getOrderList().get(k)).getShirtID())), orderArrList.get(j).getOrderList().get(k).getName(),orderArrList.get(j).getOrderList().get(k).getQty(),orderArrList.get(j).getOrderList().get(k).getTotalPrice());
                    } else if(orderArrList.get(j).getOrderList().get(k) instanceof Shoe){
                        System.out.printf("%-10s | %-25s | %-14s | %-10.2f%n",((((Shoe) orderArrList.get(j).getOrderList().get(k)).getShoeID())), orderArrList.get(j).getOrderList().get(k).getName(),orderArrList.get(j).getOrderList().get(k).getQty(),orderArrList.get(j).getOrderList().get(k).getTotalPrice());
                    }
                totalPrice += orderArrList.get(j).getOrderList().get(k).getTotalPrice();
            }
        }
        for (int i = 0; i < 68; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.printf("Total Price: RM%.2f%n", totalPrice);
        DriverProgram.pressAnyKeyToContinueWithPrompt();
        DriverProgram.adminMenu(adminArrList, customerArrList, admin);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "staffID='" + staffID + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Admin admin = (Admin) o;

        return staffID != null ? staffID.equals(admin.staffID) : admin.staffID == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (staffID != null ? staffID.hashCode() : 0);
        return result;
    }
}

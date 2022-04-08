import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

//    public void modifyProduct(Object obj) {
//        int selection;
//        String productID;
//        String productName;
//
//        Scanner scanner = new Scanner("System.in");
//        System.out.println("Modify Product");
//        System.out.println("-----------------");
//        System.out.println("Do you wish to search the product by ID or name?");
//        System.out.println("1. Product ID");
//        System.out.println("2. Product Name");
//        System.out.print("Selection: ");
//        selection = scanner.nextInt();
//
//        switch (selection) {
//            case 1:
//                System.out.print("Please Enter Product ID: ");
//                productID = scanner.next();
//
//                for (product:ProductMixCart.productArrayList)
//
//                if (obj instanceof Shirt) {
//                }
//
//
//            case 2:
//                System.out.println("Please Enter Product Name: ");
//                productName = scanner.nextLine();
//        }
//    }


    // report feature
}

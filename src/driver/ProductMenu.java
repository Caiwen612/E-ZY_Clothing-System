//package driver;
//
//import product.*;
//import utility.Font;
//import utility.Validation;
//import utility.ValidationException;
//
//import java.util.ArrayList;
//import java.util.InputMismatchException;
//import java.util.Objects;
//import java.util.Scanner;
//
//public class ProductMenu {
//    static Scanner input = new Scanner(System.in);
//
//    ArrayList<Product> productArrayList = new ArrayList<>();
//
//    public static void main(String[] args) throws InterruptedException {
//        //Create arraylist to store product object
//        ArrayList<Product> productArrayList = new ArrayList<>();
//        //Sample data of product
//        setProducts(productArrayList);
//        //Display product menu
//        productMenu();
//        // Get input for cart option
//        boolean cartOptionError = true;
//        int cartOption = 0;
//        do {
//            try {
//                System.out.print("Enter your option: ");
//                cartOption = input.nextInt();
//                Validation.validOption(cartOption, 1, 4);
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
//        clearScreen();
//        switch (cartOption) {
//            case 1:
//                searchProduct(productArrayList);
//                break;
//            case 2:
//                category(productArrayList);
//                break;
//            case 3:
//                bestSales(productArrayList);
//            case 4:
//            case 5:
//        }
//
//    }
//
//    private static void productMenu() {
//        System.out.println("\t\t+-----------+");
//        System.out.println("\t\t|  product.Product  |");
//        System.out.println("\t\t+-----------+");
//        System.out.println("\n[1] Search product ");
//        System.out.println("[2] Category ");
//        System.out.println("[3] Best Sales ");
//        System.out.println("[4] Back to previous Menu ");
//    }
//
//    private static void setProducts(ArrayList<Product> productArrayList) {
//        //product.Shirt
//        Product p1 = new Shirt("Crew Tee", 25.30, 20, 3);
//        Product p2 = new Shirt("Hoodie", 50.20, 10, 4);
//        Product p3 = new Shirt("Jacket", 67.70, 30, 2);
//        Product p4 = new Shirt("Poplin product.Shirt", 43.80, 40, 4);
//        Product p5 = new Shirt("Dress", 40.50, 30, 5);
//        Product p6 = new Shirt("Jumpsuit", 70.60, 25, 3);
//        Product p7 = new Shirt("Sport wear", 80.30, 66, 5);
//        //product.Pant
//        Product p8 = new Pant("Jeans", 30.50, 12, 5);
//        Product p9 = new Pant("Trousers", 40.20, 5, 5);
//        Product p10 = new Pant("Cotton pants", 35.30, 20, 4);
//        Product p11 = new Pant("Short pan", 15.60, 26, 3);
//        Product p12 = new Pant("Ripped pants", 43.20, 19, 2);
//        Product p13 = new Pant("Sport pan", 30.60, 45, 4);
//        Product p14 = new Pant("Cropped pant", 39.20, 45, 1);
//        //product.Accessories
//        Product p15 = new Accessories("Cap", 14.60, 30, 2);
//        Product p16 = new Accessories("Short wallet", 20.60, 25, 3);
//        Product p17 = new Accessories("Long wallet", 30.60, 17, 2);
//        Product p18 = new Accessories("Earring", 10.30, 30, 5);
//        Product p19 = new Accessories("Headband", 15.40, 50, 4);
//        Product p20 = new Accessories("Sunglasses", 12.50, 45, 3);
//        //product.Shoe
//        Product p21 = new Shoe("Running shoe", 60, 25, 4);
//        Product p22 = new Shoe("Leather shoe", 300, 10, 5);
//        Product p23 = new Shoe("Basketball shoe", 200, 25, 3);
//        Product p24 = new Shoe("Badminton shoe", 180, 16, 3);
//        Product p25 = new Shoe("High heel shoe", 66.80, 30, 5);
//        Product p26 = new Shoe("Relax Spot shoe", 100.30, 20, 5);
//
//        //Add all product to arraylist
//        productArrayList.add(p1);
//        productArrayList.add(p2);
//        productArrayList.add(p3);
//        productArrayList.add(p4);
//        productArrayList.add(p5);
//        productArrayList.add(p6);
//        productArrayList.add(p7);
//        productArrayList.add(p8);
//        productArrayList.add(p9);
//        productArrayList.add(p10);
//        productArrayList.add(p11);
//        productArrayList.add(p12);
//        productArrayList.add(p13);
//        productArrayList.add(p14);
//        productArrayList.add(p15);
//        productArrayList.add(p16);
//        productArrayList.add(p17);
//        productArrayList.add(p18);
//        productArrayList.add(p19);
//        productArrayList.add(p20);
//        productArrayList.add(p21);
//        productArrayList.add(p22);
//        productArrayList.add(p23);
//        productArrayList.add(p24);
//        productArrayList.add(p25);
//        productArrayList.add(p26);
//    }
//
//    public static void searchProduct(ArrayList<Product> productArrayList) throws InterruptedException {
//        System.out.println("\t\t+------------------+");
//        System.out.println("\t\t|  Search product.Product  |");
//        System.out.println("\t\t+------------------+");
//        System.out.println("[1] product.Product ID");
//        System.out.println("[2] product.Product Name");
//        System.out.println("[3] Price Range");
//        System.out.println();
//        // Get input for cart option
//        boolean searchOptionError = true;
//        int searchOption = 0;
//        do {
//            try {
//                System.out.print("Enter your option: ");
//                searchOption = input.nextInt();
//                Validation.validOption(searchOption, 1, 3);
//                searchOptionError = false;
//            } catch (ValidationException e) {
//                System.err.println(e.getMessage());
//                Thread.sleep(1000);
//            } catch (InputMismatchException e) {
//                // Customize the message for let user know
//                System.err.println(Font.useFont(Font.BOLD_RED, "Please only key in integer"));
//                input.nextLine();
//                Thread.sleep(1000);
//            }
//        } while (searchOptionError);
//        switch (searchOption) {
//            case 1:
//                clearScreen();
//                System.out.println("T For product.Shirt");
//                System.out.println("P For product.Pant");
//                System.out.println("A For product.Accessories");
//                System.out.println("S For product.Shoe");
//                System.out.println("Format: T/P/A/S + product.Product ID");
//                input.nextLine();
//                System.out.print("Enter the ID that you want to search: ");
//                String targetProductID = input.nextLine().toUpperCase();
//                char filterProductID = targetProductID.charAt(0);
//                while (!Validation.validProductID(filterProductID)) {
//                    System.out.print("Enter the ID that you want to search: ");
//                    targetProductID = input.nextLine().toUpperCase();
//                    filterProductID = targetProductID.charAt(0);
//                }
//                System.out.println(targetProductID);
//                //Perform fast linear search with filter id
//                for (Product product : productArrayList) {
//                    if (filterProductID == 'T' && product instanceof Shirt) {
//                        if (Objects.equals(((Shirt) product).getShirtID(), targetProductID)) {
//                            System.out.println(product);
//                            break;
//                        }
//                    } else if (filterProductID == 'P' && product instanceof Pant) {
//                        if (Objects.equals(((Pant) product).getPantID(), targetProductID)) {
//                            System.out.println(product);
//                            break;
//                        }
//
//                    } else if (filterProductID == 'A' && product instanceof Accessories) {
//                        if (Objects.equals(((Accessories) product).getAccessoriesID(), targetProductID)) {
//                            System.out.println(product);
//                            break;
//                        }
//                    } else if (filterProductID == 'S' && product instanceof Shoe) {
//                        if (Objects.equals(((Shoe) product).getShoeID(), targetProductID)) {
//                            System.out.println(product);
//                            break;
//                        }
//                    }
//                }
//                break;
//            case 2: //Search by name
//                input.nextLine();
//                System.out.print("Enter the name that you want to search: ");
//                String targetProductName = input.nextLine().toUpperCase();
//                System.out.println(targetProductName);
//                for (Product product : productArrayList) {
//                    if (Objects.equals(product.getName().toUpperCase(), targetProductName) || product.getName().toUpperCase().contains(targetProductName)) {
//                        System.out.println(product);
//                    }
//                }
//                break;
//            case 3: //Search by price range
//                input.nextLine();
//                System.out.print("Enter the lower price that you want to search for: RM");
//                double lowerPrice = input.nextDouble();
//                System.out.print("Enter the upper price that you want to search for: RM");
//                double upperPrice = input.nextDouble();
//                if(lowerPrice > upperPrice){
//                    System.out.println("Invalid price range: Lower price must be less than upper price");
//                } else{
//                    for(Product product : productArrayList){
//                        if(product.getPrice() >= lowerPrice && product.getPrice() <= upperPrice){
//                            System.out.println(product);
//                        }
//                    }
//                }
//                break;
//
//
//
//        }
//        System.out.println("Do you want to search again? (Y/N)");
//        System.out.println("Do you want to add this product to your cart? (Y/N)");
//
//
//
//    }
//
//    public static void category(ArrayList<Product> productArrayList) throws InterruptedException {
//        System.out.println("\t\t+------------+");
//        System.out.println("\t\t|  Category  |");
//        System.out.println("\t\t+------------+");
//        System.out.println("[1] product.Shirt");
//        System.out.println("[2] product.Pant");
//        System.out.println("[3] product.Accessories");
//        System.out.println("[4] Shoes");
//
//        // Get input for cart option
//        boolean categoryOptionError = true;
//        int categoryOption = 0;
//        do {
//            try {
//                System.out.print("\nEnter your option: ");
//                categoryOption = input.nextInt();
//                Validation.validOption(categoryOption, 1, 4);
//                categoryOptionError = false;
//            } catch (ValidationException e) {
//                System.err.println(e.getMessage());
//                Thread.sleep(1000);
//            } catch (InputMismatchException e) {
//                // Customize the message for let user know
//                System.err.println(Font.useFont(Font.BOLD_RED, "Please only key in integer"));
//                input.nextLine();
//                Thread.sleep(1000);
//            }
//        } while (categoryOptionError);
//
//        //Display which category
//        System.out.println("product.Product ID\t\t" + "product.Product Name\t\t\t" + "product.Product Price\t\t" + "Quantity In Stock");
//        switch (categoryOption) {
//            case 1:
//                //Display product shirt
//                for (Product product : productArrayList) {
//                    if (product instanceof Shirt) {
//                        System.out.println(product);
//                    }
//                }
//                break;
//            case 2:
//                //Display product pant
//                for (Product product : productArrayList) {
//                    if (product instanceof Pant) {
//                        System.out.println(product);
//                    }
//                }
//                break;
//            case 3:
//                //Display product accessories
//                for (Product product : productArrayList) {
//                    if (product instanceof Accessories) {
//                        System.out.println(product);
//                    }
//                }
//                break;
//            case 4:
//                //Display product shoe
//                for (Product product : productArrayList) {
//                    if (product instanceof Shoe) {
//                        System.out.println(product);
//                    }
//                }
//                break;
//        }
//    }
//
//    public static void bestSales (ArrayList<Product> productArrayList){
//        System.out.println("\t\t+--------------+");
//        System.out.println("\t\t|  Best Sales  |");
//        System.out.println("\t\t+--------------+");
//
//        for(Product product : productArrayList){
//            if(product.getRating() == 5){
//                System.out.println(product);
//            }
//        }
//    }
//
//    public static void clearScreen(){
////        try{
////            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
////        }
////        catch(Exception e){
////            System.out.println(e);
////        }
//        for (int i = 0; i < 50; i++) {
//            System.out.println();
//        }
//    }
//
//
//}
//
//
//
//
//
//
//
//
//

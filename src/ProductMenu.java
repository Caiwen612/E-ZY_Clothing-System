import utility.Font;
import utility.Validation;
import utility.ValidationException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class ProductMenu {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args)throws InterruptedException {
        //Product
        //Shirt
        Product p1 = new Shirt("Crew Tee",25.30,20);
        Product p2 = new Shirt("Hoodie",50.20,10);
        Product p3 = new Shirt("Jacket",67.70,30);
        Product p4 = new Shirt("Poplin Shirt",43.80,40);
        Product p5 = new Shirt("Dress",40.50,30);
        Product p6 = new Shirt("Jumpsuit",70.60,25);
        Product p7 = new Shirt("Sport wear",80.30,66);
        //Pant
        Product p8 = new Pant("Jeans",30.50,12);
        Product p9 = new Pant("Trousers",40.20,5);
        Product p10 = new Pant("Cotton pants",35.30,20);
        Product p11 = new Pant("Short pan",15.60,26);
        Product p12 = new Pant("Ripped pants",43.20,19);
        Product p13 = new Pant("Sport pan",30.60,45);
        Product p14 = new Pant("Cropped pant",39.20,45);
        //Accessories
        Product p15 = new Accessories("Cap",14.60,30);
        Product p16 = new Accessories("Short wallet",20.60,25);
        Product p17 = new Accessories("Long wallet",30.60,17);
        Product p18 = new Accessories("Earring",10.30,30);
        Product p19 = new Accessories("Headband",15.40,50);
        Product p20 = new Accessories("Sunglasses",12.50,45);
        //Shoe
        Product p21 = new Shoe("Running shoe",60,25);
        Product p22 = new Shoe("Leather shoe",300,10);
        Product p23 = new Shoe("Basketball shoe",200,25);
        Product p24 = new Shoe("Badminton shoe",180,16);
        Product p25 = new Shoe("High heel shoe",66.80,30);
        Product p26 = new Shoe("Relax Spot shoe",100.30,20);

        //Declare  product array
        ArrayList<Product> productArrayList = new ArrayList<Product>(){
            {
                add(p1);
                add(p2);
                add(p3);
                add(p4);
                add(p5);
                add(p6);
                add(p7);
                add(p8);
                add(p9);
                add(p10);
                add(p11);
                add(p12);
                add(p13);
                add(p14);
                add(p15);
                add(p16);
                add(p17);
                add(p18);
                add(p19);
                add(p20);
                add(p21);
                add(p22);
                add(p23);
                add(p24);
                add(p25);
                add(p26);
            }
        };


        System.out.println("\t\t+-----------+");
        System.out.println("\t\t|  Product  |");
        System.out.println("\t\t+-----------+");
        System.out.println("\n[1] Search product ");
        System.out.println("[2] Category ");
//        System.out.println("[3] Best Sales ");
        System.out.println("[4] Back to previous Menu ");

        // Get input for cart option
        boolean cartOptionError = true;
        int cartOption = 0;
        do {
            try {
                System.out.print("Enter your option: ");
                cartOption = input.nextInt();
                Validation.validOption(cartOption,1,4);
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
        switch (cartOption){
            case 1:
                searchProduct(productArrayList);
                break;
            case 2:
                category(productArrayList);
                break;
            case 3:
            case 4:
            case 5:
        }

    }

    public static void searchProduct(ArrayList<Product> productArrayList) throws InterruptedException {
        System.out.println("\t\t+------------------+");
        System.out.println("\t\t|  Search Product  |");
        System.out.println("\t\t+------------------+");
        System.out.println("[1] Product ID");
        System.out.println("[2] Product Name");
        System.out.println("[3] Price Range");
        System.out.println();
        // Get input for cart option
        boolean searchOptionError = true;
        int searchOption = 0;
        do {
            try {
                System.out.print("Enter your option: ");
                searchOption = input.nextInt();
                Validation.validOption(searchOption,1,3);
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
        switch (searchOption){
            case 1:
                input.nextLine();
                System.out.print("Enter the ID that you want to search: ");
                String targetProductID = input.nextLine().toUpperCase();
                char filterProductID = targetProductID.charAt(0);
                while (!Validation.validProductID(filterProductID)) {
                    System.out.print("Enter the ID that you want to search: ");
                    targetProductID = input.nextLine().toUpperCase();
                    filterProductID = targetProductID.charAt(0);
                }
                System.out.println(targetProductID);
                    //Perform fast linear search with filter id
                    for (Product product : productArrayList) {
                        if (filterProductID == 'T' && product instanceof Shirt) {
                            if (Objects.equals(((Shirt) product).getShirtID(), targetProductID)) {
                                System.out.println(product);
                                break;
                            }
                        }
                        else if(filterProductID == 'P' && product instanceof Pant){
                            if (Objects.equals(((Pant) product).getPantID(), targetProductID)) {
                                System.out.println(product);
                                break;
                            }

                        }
                        else if(filterProductID == 'A' && product instanceof Accessories){
                            if (Objects.equals(((Accessories) product).getAccessoriesID(), targetProductID)) {
                                System.out.println(product);
                                break;
                            }
                        }
                        else if(filterProductID == 'S' && product instanceof Shoe){
                            if (Objects.equals(((Shoe) product).getShoeID(), targetProductID)) {
                                System.out.println(product);
                                break;
                            }
                        }
                    }
                    break;
                    case 2: //Search by name
                        input.nextLine();
                        System.out.print("Enter the name that you want to search: ");
                        String targetProductName = input.nextLine().toUpperCase();
                        System.out.println(targetProductName);
                        for (Product product : productArrayList) {
                            if (Objects.equals(product.getName().toUpperCase(), targetProductName) || product.getName().toUpperCase().contains(targetProductName)) {
                                System.out.println(product);
                            }
                        }
                        break;



                }

        }


    public static void category(ArrayList<Product> productArrayList) throws InterruptedException {
        System.out.println("\t\t+------------+");
        System.out.println("\t\t|  Category  |");
        System.out.println("\t\t+------------+");
        System.out.println("[1] Shirt");
        System.out.println("[2] Pant");
        System.out.println("[3] Accessories");
        System.out.println("[4] Shoes");

        // Get input for cart option
        boolean categoryOptionError = true;
        int categoryOption = 0;
        do {
            try {
                System.out.print("\nEnter your option: ");
                categoryOption = input.nextInt();
                Validation.validOption(categoryOption,1,4);
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
        System.out.println("Product ID\t\t" + "Product Name\t\t\t" + "Product Price\t\t" + "Quantity In Stock");
        switch (categoryOption) {
            case 1:
                //Display product shirt
                for (Product product : productArrayList) {
                    if (product instanceof Shirt) {
                        System.out.println(product);
                    }
                }
                break;
            case 2:
                //Display product pant
                for (Product product : productArrayList) {
                    if (product instanceof Pant) {
                        System.out.println(product);
                    }
                }
                break;
            case 3:
                //Display product accessories
                for (Product product : productArrayList) {
                    if (product instanceof Accessories) {
                        System.out.println(product);
                    }
                }
                break;
            case 4:
                //Display product shoe
                for (Product product : productArrayList) {
                    if (product instanceof Shoe) {
                        System.out.println(product);
                    }
                }
                break;
        }
        }







    


    




}

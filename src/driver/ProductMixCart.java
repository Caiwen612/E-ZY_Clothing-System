package driver;

import cart.Cart;
import product.*;
import utility.Font;
import utility.Validation;
import utility.ValidationException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class ProductMixCart {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        //Create arraylist to store product object
        //Initialize data
        ArrayList<Product> productArrayList = new ArrayList<>();
        Cart cart = new Cart();
        //Start
        setProducts(productArrayList);
        //Testing purpose
//        for(product.Product p : productArrayList){
//            cart.addItem(p.clone(),15);
//        }
        cart.addItem(productArrayList.get(2).clone(),15);
        cart.addItem(productArrayList.get(3).clone(),6);
        cart.addItem(productArrayList.get(4).clone(),4);
        cart.addItem(productArrayList.get(5).clone(),5);
        cart.addItem(productArrayList.get(6).clone(),2);
        cart.addItem(productArrayList.get(7).clone(),6);

        //Testing
        productMenu(productArrayList,cart);
        searchProductByProductID(productArrayList, cart);

        cartMenu(productArrayList,cart);

    }

    private static void setProducts(ArrayList<Product> productArrayList) {
        //product.Shirt
        Product p1 = new Shirt("Crew Tee", 25.30, 20, 3);
        Product p2 = new Shirt("Hoodie", 50.20, 10, 4);
        Product p3 = new Shirt("Jacket", 67.70, 30, 2);
        Product p4 = new Shirt("Poplin product.Shirt", 43.80, 40, 4);
        Product p5 = new Shirt("Dress", 40.50, 30, 5);
        Product p6 = new Shirt("Jumpsuit", 70.60, 25, 3);
        Product p7 = new Shirt("Sport wear", 80.30, 66, 5);
        //product.Pant
        Product p8 = new Pant("Jeans", 30.50, 12, 5);
        Product p9 = new Pant("Trousers", 40.20, 5, 5);
        Product p10 = new Pant("Cotton pants", 35.30, 20, 4);
        Product p11 = new Pant("Short pan", 15.60, 26, 3);
        Product p12 = new Pant("Ripped pants", 43.20, 19, 2);
        Product p13 = new Pant("Sport pan", 30.60, 45, 4);
        Product p14 = new Pant("Cropped pant", 39.20, 45, 1);
        //product.Accessories
        Product p15 = new Accessories("Cap", 14.60, 30, 2);
        Product p16 = new Accessories("Short wallet", 20.60, 25, 3);
        Product p17 = new Accessories("Long wallet", 30.60, 17, 2);
        Product p18 = new Accessories("Earring", 10.30, 30, 5);
        Product p19 = new Accessories("Headband", 15.40, 50, 4);
        Product p20 = new Accessories("Sunglasses", 12.50, 45, 3);
        //product.Shoe
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

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~TODO: cart.Cart Menu ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public static void cartMenu(ArrayList<Product> productArrayList, Cart cart) throws InterruptedException {
        //Create a new cart
        clearScreen();
        System.out.print(Font.TEXT_CYAN);
        System.out.printf(  "%55s",  "+------------+");
        System.out.printf("%n%55s","|    cart.Cart    |");
        System.out.printf("%n%55s","+------------+");
        System.out.print(Font.RESET);

        //Display cart
        System.out.println();
        System.out.println(" +=================================================================================================+   ");
        System.out.print(" |");
        //Setup for cart title
        System.out.printf("%12s", "cart.Cart No  |");
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
        System.out.printf("%n%60s",  "[5] Make payment.Payment   ");
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
                addItem(productArrayList, cart);
                break;
            case 2:
                editItem(productArrayList,cart);
                break;
            case 3:
                removeItem(productArrayList,cart);
                break;
            case 4:
                sortItem(productArrayList,cart);
                break;
            case 5:
                //payment
                break;
            case 6:
                //back to previous menu
                break;
        }
    }

    public static void addItem(ArrayList<Product> productArrayList, Cart cart) throws InterruptedException {
        productMenu(productArrayList,cart);
    }

    public static void editItem(ArrayList<Product> productArrayList, Cart cart) throws InterruptedException {
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
            System.out.printf("%n%63s","Press enter key to go back cart.Cart menu");
            pressAnyKeyToContinue();
            cartMenu(productArrayList,cart);
        } else{
            System.out.println(Font.TEXT_YELLOW);
            System.out.printf("%63s","Press enter key to go back cart.Cart menu");
            pressAnyKeyToContinue();
            cartMenu(productArrayList,cart);
        }

    }

    public static void removeItem(ArrayList<Product> productArrayList, Cart cart) throws InterruptedException {
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
            System.out.printf("%n%63s","Press enter key to go back cart.Cart menu");
            pressAnyKeyToContinue();
            cartMenu(productArrayList,cart);

        } else{
            System.out.println(Font.TEXT_YELLOW);
            System.out.printf("%63s","Press enter key to go back cart.Cart menu");
            pressAnyKeyToContinue();
            cartMenu(productArrayList,cart);
        }
    }

    public static void sortItem(ArrayList<Product> productArrayList, Cart cart) throws InterruptedException {
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
        System.out.printf("%n%71s",("[3] Sort by price " + Font.TEXT_YELLOW + "[ASC]" + Font.RESET));
        System.out.printf("%n%72s",("[4] Sort by price " + Font.TEXT_YELLOW + "[DESC]" + Font.RESET));
        System.out.printf("%n%74s",("[5] Sort by quantity " + Font.TEXT_YELLOW + "[ASC]" + Font.RESET));
        System.out.printf("%n%75s",("[6] Sort by quantity " + Font.TEXT_YELLOW + "[DESC]" + Font.RESET));
        System.out.printf("%n%85s",("[7] Sort by qty and total price " + Font.TEXT_YELLOW + "[ASC]" + Font.RESET));
        System.out.printf("%n%86s",("[8] Sort by qty and total price " + Font.TEXT_YELLOW + "[DESC]" + Font.RESET));
        System.out.printf("%n%61s","[9] Back to cart.Cart Menu ");

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
        System.out.printf("%67s","Press enter key to go back cart.Cart menu");
        pressAnyKeyToContinue();
        cartMenu(productArrayList,cart);
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~TODO: End cart.Cart Menu~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~TODO: product.Product Menu ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    public static void productMenu(ArrayList<Product> productArrayList,Cart cart) throws InterruptedException {
        //Display product menu
        clearScreen();
        System.out.print(Font.TEXT_CYAN);
        System.out.printf(  "%59s", "+---------------+");
        System.out.printf("%n%59s", "|    product.Product    |");
        System.out.printf("%n%59s", "+---------------+");
        System.out.print(Font.RESET);
        System.out.println();
        System.out.printf(  "%64s", "[1] Search product        ");
        System.out.printf("%n%64s", "[2] Category              ");
        System.out.printf("%n%64s", "[3] Back to previous Menu ");
        System.out.printf("%n%64s", "[4] Back to cart.Cart Menu     ");
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
                searchProductMenu(productArrayList, cart);
                break;
            case 2:
                categoryMenu(productArrayList, cart);
                break;
            case 3:
                //??
                break;
            case 4:
                cartMenu(productArrayList,cart);
                break;
        }

    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~TODO: Start Search~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //***************TODO: Different Search Option***************//
    public static void searchProductMenu(ArrayList<Product> productArrayList, Cart cart) throws InterruptedException {
        clearScreen();
        System.out.print(Font.TEXT_CYAN);
        System.out.printf(  "%66s", "+----------------------+");
        System.out.printf("%n%66s", "|    Search product.Product    |");
        System.out.printf("%n%66s", "+----------------------+");
        System.out.print(Font.RESET);
        System.out.printf("%n%71s", "[1] product.Product ID            ");
        System.out.printf("%n%71s", "[2] product.Product Name          ");
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
                searchProductByProductID(productArrayList, cart);
                break;
            case 2: //Search by name
                searchProductByProductName(productArrayList, cart);
                break;
            case 3: //Search by price range
                searchProductByPriceRange(productArrayList, cart);
                break;
            case 4: //Back to previous menu
                productMenu(productArrayList,cart);
                break;
        }

    }

    private static void searchProductByProductID(ArrayList<Product> productArrayList, Cart cart) throws InterruptedException {
        clearScreen();
        System.out.print(Font.TEXT_YELLOW);
        System.out.printf("%n%68s", "T For product.Shirt               ");
        System.out.printf("%n%68s", "P For product.Pant                ");
        System.out.printf("%n%68s", "A For product.Accessories         ");
        System.out.printf("%n%68s", "S For product.Shoe                ");
        System.out.printf("%n%83s", "Format: T/P/A/S + product.Product ID             ");
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
            System.out.printf("%n%64s", "product.Product ID not found");
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
                searchProductByProductID(productArrayList, cart);
            } else {
                searchProductMenu(productArrayList, cart);
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
                    searchProductByProductID(productArrayList, cart);
                } else {
                    searchProductMenu(productArrayList, cart);
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
                    searchProductByProductID(productArrayList, cart);
                } else {
                    searchProductMenu(productArrayList, cart);
                }
            }

        }

    }

    private static void searchProductByProductName(ArrayList<Product> productArrayList, Cart cart) throws InterruptedException {
        clearScreen();
        input.nextLine();
        System.out.print("Enter the name that you want to search: ");
        String targetProductName = input.nextLine().toUpperCase();
        ArrayList<Product> searchResultList = linearSearchByProductName(productArrayList, targetProductName);
        if (searchResultList.size() == 0) {
            System.out.println("product.Product not found");
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
                searchProductByProductName(productArrayList, cart);
            } else {
                searchProductMenu(productArrayList, cart);
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
                    System.out.println("product.Product not found");
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
                    searchProductByProductName(productArrayList, cart);
                } else {
                    searchProductMenu(productArrayList, cart);
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
                    searchProductByProductName(productArrayList, cart);
                } else {
                    searchProductMenu(productArrayList, cart);
                }
            }
        }
    }

    private static void searchProductByPriceRange(ArrayList<Product> productArrayList, Cart cart) throws InterruptedException {
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
            System.out.println("product.Product not found");
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
                searchProductByPriceRange(productArrayList, cart);
            } else {
                searchProductMenu(productArrayList, cart);
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
                    System.out.println("product.Product not found");
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
                    searchProductByPriceRange(productArrayList, cart);
                } else {
                    searchProductMenu(productArrayList, cart);
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
                    searchProductByPriceRange(productArrayList, cart);
                } else {
                    searchProductMenu(productArrayList, cart);
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
    public static void categoryMenu(ArrayList<Product> productArrayList, Cart cart) throws InterruptedException {
        clearScreen();
        System.out.println("\t\t+------------+");
        System.out.println("\t\t|  Category  |");
        System.out.println("\t\t+------------+");
        System.out.println("[1] product.Shirt");
        System.out.println("[2] product.Pant");
        System.out.println("[3] product.Accessories");
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
                Category(productArrayList, cart,"product.Shirt");
                break;
            case 2:
                Category(productArrayList, cart,"product.Pant");
                break;
            case 3:
                Category(productArrayList, cart,"product.Accessories");
                break;
            case 4:
                Category(productArrayList, cart,"product.Shoe");
                break;
            case 5:
                Category(productArrayList, cart,"Best Sales");
                break;
            case 6:
                productMenu(productArrayList,cart);
                break;
        }
    }

    private static void Category(ArrayList<Product> productArrayList, Cart cart, String category) throws InterruptedException {
        //Display product shirt
        ArrayList<Product> categoryResultList = linearSearchByProductCategory(productArrayList, category);
        System.out.println("product.Product ID\t\t" + "product.Product Name\t\t\t" + "product.Product Price\t\t" + "Quantity In Stock");
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
                System.out.println("product.Product not found");
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
            categoryMenu(productArrayList, cart);
        } else{
            System.out.println("Press enter key to go back category menu");
            pressAnyKeyToContinue();
            categoryMenu(productArrayList, cart);
        }
    }

    private static ArrayList<Product> linearSearchByProductCategory(ArrayList<Product> productArrayList, String category) {
        ArrayList<Product> categoryResultList = new ArrayList<>();
        switch (category) {
            case "product.Shirt":
                for (Product product : productArrayList) {
                    if(product instanceof Shirt) {
                        categoryResultList.add(product);
                    }
                }
                break;
            case "product.Pant":
                for (Product product : productArrayList) {
                    if (product instanceof Pant) {
                        categoryResultList.add(product);
                    }
                }
                break;
            case "product.Shoe":
                for (Product product : productArrayList) {
                    if (product instanceof Shoe) {
                        categoryResultList.add(product);
                    }
                }
                break;
            case "product.Accessories":
                for (Product product : productArrayList) {
                    if (product instanceof Accessories) {
                        categoryResultList.add(product);
                    }
                }
                break;
            case "Best Sales":
                System.out.println("\t\t+--------------+");
                System.out.println("\t\t|  Best Sales  |");
                System.out.println("\t\t+--------------+");
                for (Product product : productArrayList) {
                    if (product.getRating() == 5) {
                        categoryResultList.add(product);
                    }
                }
        }

        return categoryResultList;
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~TODO: End Category & Best Sales~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~TODO: End product.Product Menu ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//

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
        input.nextLine();
        input.nextLine();
    }

    }


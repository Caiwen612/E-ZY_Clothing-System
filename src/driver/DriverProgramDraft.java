//package driver;
//
//import product.*;
//
//import java.io.*;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class DriverProgramDraft {
//    //Declare a set of arrayList
//
//    public static ArrayList<Product> productArrayList = new ArrayList<>();
//
//
//
//    static Scanner input = new Scanner(System.in);
//    static DecimalFormat df2 = new DecimalFormat("0.00");
//
//    public static void main(String[] args) {
//        // TODO: Initialize the arrayList
//        loadArrayList();
//
//        setProducts(productArrayList);
//
//        storeArrayList();//Use for exit programs
//
//        if(file1.exists()){
//            System.out.println("Store files inslide");
//            for (Product product : productArrayList) {
//                System.out.println(product.getName());
//            }
//            loadArrayList();
//        } else{
//            System.out.println("Read files");
//            for (Product product : productArrayList) {
//                System.out.println(product.getName());
//            }
//            storeArrayList();
//        }
//
//    }
//
//    //Store arraylist from files
//    public static void storeArrayList() {
//        try {
//            FileOutputStream fos = new FileOutputStream("product.txt");
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(productArrayList);
//            oos.close();
//            System.out.println("The object of the programs is stored in the file");
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    //load arraylist from files
//    public static void loadArrayList(){
//        File file1 = new File("product.txt");
//        if (file1.exists()) { //If the files exists, load all data into driver program
//            try {
//                FileInputStream fis = new FileInputStream("product.txt");
//                ObjectInputStream ois = new ObjectInputStream(fis);
//                productArrayList = (ArrayList<Product>) ois.readObject();
//                ois.close();
//                System.out.println("The object of the programs is loaded from the file");
//            } catch(Exception e) {
//                e.printStackTrace();
//            }
//        } else {
//            //Store all of the data into the files
//            storeArrayList();
//        }
//    }
//
//
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
//
//    }
//
//    }
//
//
//
//
//
//
//

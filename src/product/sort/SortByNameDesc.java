package product.sort;

import product.Product;
import java.util.Comparator;

//Author: Tay Chai Boon

public class SortByNameDesc implements Comparator<Product> {
    public int compare(Product product1, Product product2) {
        String productName1 = product1.getName().toUpperCase();
        String productName2 = product2.getName().toUpperCase();

        return productName2.compareTo(productName1);
    }
}

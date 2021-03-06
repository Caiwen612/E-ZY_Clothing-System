package product.sort;

import product.Product;
import java.util.Comparator;

//Author: Tay Chai Boon

public class SortByQtyPriceDesc implements Comparator<Product> {
    public int compare(Product product1, Product product2) {

        //Compare product
        int qtyCompare = product2.getQty() - product1.getQty();
        int priceCompare = Double.compare(product2.getTotalPrice(), product1.getTotalPrice());

        //If qty is same, compare price
        //condition ? if true:false
        return (qtyCompare == 0) ? priceCompare : qtyCompare;
    }
}

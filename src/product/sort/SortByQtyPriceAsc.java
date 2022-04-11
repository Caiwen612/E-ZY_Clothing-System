package product.sort;

import product.Product;
import java.util.Comparator;

//Author: Tay Chai Boon

public class SortByQtyPriceAsc implements Comparator<Product> {
    public int compare(Product product1, Product product2) {

        //Compare product
        int qtyCompare = product1.getQty() - product2.getQty();
        int priceCompare = Double.compare(product1.getTotalPrice(), product2.getTotalPrice());

        //If qty is same, compare price
        //condition ? if true:false
        return (qtyCompare == 0) ? priceCompare : qtyCompare;
    }
}

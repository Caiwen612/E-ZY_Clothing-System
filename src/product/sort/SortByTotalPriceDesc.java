package product.sort;

import product.Product;
import java.util.Comparator;

//Author: Tay Chai Boon

public class SortByTotalPriceDesc implements Comparator<Product> {
    public int compare(Product product1, Product product2) {
        double productTotalPrice1 = product1.getTotalPrice();
        double productTotalPrice2 = product2.getTotalPrice();

        return Double.compare(productTotalPrice2, productTotalPrice1);
    }
}

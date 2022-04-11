package product.sort;

import product.Product;
import java.util.Comparator;

public class SortByQtyDesc implements Comparator<Product> {
    public int compare(Product product1, Product product2) {
        int productQty1 = product1.getQty();
        int productQty2 = product2.getQty();

        return productQty2 - productQty1;
    }
}

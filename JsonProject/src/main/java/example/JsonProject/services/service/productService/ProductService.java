package example.JsonProject.services.service.productService;


import example.JsonProject.models.Product;
import example.JsonProject.services.seed.ProductSeed;
import example.JsonProject.services.views.ProductsViewJson;
import example.JsonProject.services.views.UserViewSecond;

import java.util.List;
import java.util.Set;


public interface ProductService {
    void createProduct(ProductSeed productSeed);

    Set<ProductsViewJson> getProducts();

    List<UserViewSecond> getAllSellersWithBuyers();

    List<Product> productFromSellerId(long id);


}

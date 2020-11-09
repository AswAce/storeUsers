package example.JsonProject.services.service.productService;

import example.JsonProject.models.Category;
import example.JsonProject.models.Product;
import example.JsonProject.models.User;
import example.JsonProject.repositories.ProductRepository;
import example.JsonProject.services.seed.ProductSeed;
import example.JsonProject.services.service.categoryService.CategoryService;
import example.JsonProject.services.service.userService.UserService;
import example.JsonProject.services.views.ProductVIewBuyer;
import example.JsonProject.services.views.ProductsViewJson;
import example.JsonProject.services.views.UserFullNameVIew;
import example.JsonProject.services.views.UserViewSecond;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, UserService userService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void createProduct(ProductSeed productSeed) {
        Product product = this.modelMapper.map(productSeed, Product.class);
        User user = this.userService.getRandomUser();
        List<Category> categories = this.categoryService.getRandomCategory();
        Random random = new Random();
        long buyerRandom = random.nextInt(2);
        if (buyerRandom == 1) {
            product.setBuyer(this.userService.getRandomUser());
        }
        product.setSeller(user);
        Set<Category> collect = new HashSet<>(categories);
        product.setCategories(collect);

        this.productRepository.saveAndFlush(product);

    }

    @Override
    public Set<ProductsViewJson> getProducts() {
        Set<Product> products = this.productRepository.
                findByPriceGreaterThanAndBuyerNullAndPriceLessThanOrderByPriceAsc(500, 1000);
        Set<ProductsViewJson> getProducts = new LinkedHashSet<>();
        for (Product product : products) {
            ;
            UserFullNameVIew userView = this.modelMapper.map(product.getSeller(), UserFullNameVIew.class);
            ProductsViewJson prodView = this.modelMapper.map(product, ProductsViewJson.class);
            prodView.setSellerFullName(userView.getFirstName() + " " + userView.getLastName());
            getProducts.add(prodView);
            System.out.println();
        }

        return getProducts;
    }

    @Override
    public List<UserViewSecond> getAllSellersWithBuyers() {
        List<UserViewSecond> pSB = new ArrayList<>();
        Set<Long> byBuyerIsNotNUll = this.productRepository.getUserWithBuyers();
        for (Long aLong : byBuyerIsNotNUll) {
            User uSerFromId = this.userService.getUSerFromId(aLong);
            UserViewSecond userViewSecond = this.modelMapper.map(uSerFromId, UserViewSecond.class);
            Set<Product> productsSell = uSerFromId.getProductsSell();

            Set<ProductVIewBuyer> productVIewBuyers = new HashSet<>();

            for (Product product : productsSell) {
                if (product.getBuyer() != null) {
                    User buyer = product.getBuyer();
                    UserFullNameVIew userFullNameVIew = new UserFullNameVIew();
                    userFullNameVIew.setLastName(buyer.getLastName());
                    ProductVIewBuyer productVIewBuyer = this.modelMapper.map(product, ProductVIewBuyer.class);
                    productVIewBuyer.setBuyerFirstName(buyer.getFirstName());
                    productVIewBuyer.setBuyerLastName(buyer.getLastName());
                    productVIewBuyers.add(productVIewBuyer);

                }

            }
            userViewSecond.setSoldProducts(productVIewBuyers);
            pSB.add(userViewSecond);
            System.out.println();
        }
        return pSB;
    }

    @Override
    public List<Product> productFromSellerId(long id) {
        List<Product> byBuyerEquals = this.productRepository.findByBuyerEquals(id);

        return byBuyerEquals;

    }
}

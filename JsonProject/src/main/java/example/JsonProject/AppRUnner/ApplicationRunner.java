package example.JsonProject.AppRUnner;

import com.google.gson.Gson;
import example.JsonProject.services.seed.CategorySeed;
import example.JsonProject.services.seed.ProductSeed;
import example.JsonProject.services.seed.UserSeedJSON;
import example.JsonProject.services.service.categoryService.CategoryService;
import example.JsonProject.services.service.productService.ProductService;
import example.JsonProject.services.service.userService.UserService;
import example.JsonProject.services.views.ProductsViewJson;
import example.JsonProject.services.views.UserViewSecond;
import example.JsonProject.utils.FIleIotImpl;
import example.JsonProject.utils.ValidationUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static example.JsonProject.filesPath.FilesPath.*;

@Component
public class ApplicationRunner implements CommandLineRunner {
    Scanner scanner = new Scanner(System.in);
    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final FIleIotImpl fIleIot;

    public ApplicationRunner(UserService userService, CategoryService categoryService, ProductService productService, Gson gson,
                             ValidationUtil validationUtil, FIleIotImpl fIleIot) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.fIleIot = fIleIot;
    }

    @Override
    public void run(String... args) throws Exception {

//        categoryCreation();
//        addUser();
//        productCreation();
//        exerciseOne();
        sellerProductsList();



    }

    private void sellerProductsList() throws IOException {
        List<UserViewSecond> allSellersWithBuyers = this.productService.getAllSellersWithBuyers();
        String s = gson.toJson(allSellersWithBuyers);
        this.fIleIot.write(s, SELLER_WITH_LIST_SOLD_PRODUCTS);
    }

    private void productsWithSellers() throws IOException {
        Set<ProductsViewJson> products = this.productService.getProducts();
        String s = gson.toJson(products);
        this.fIleIot.write(s, PRODUCTS_WITH_SELLER_NAME);
    }

    private void addUser() throws FileNotFoundException {
        UserSeedJSON[] user = this.gson.
                fromJson(new FileReader(USER_PATH), UserSeedJSON[].class);
        for (UserSeedJSON userSeedJSON : user) {
            if (this.validationUtil.isValid(userSeedJSON)) {
                this.userService.insertUser(userSeedJSON);
            } else {
                this.validationUtil.getViolation(userSeedJSON).
                        stream().map(ConstraintViolation::getMessage).
                        forEach(System.out::println);

            }
        }
    }

    private void productCreation() throws FileNotFoundException {
        ProductSeed[] productsDtos = this.gson.
                fromJson(new FileReader(PRODUCTS_PATH), ProductSeed[].class);
        for (ProductSeed productsDto : productsDtos) {


            if (this.validationUtil.isValid(productsDtos)) {
                this.productService.createProduct(productsDto);
            } else {
                this.validationUtil.getViolation(productsDtos).
                        stream().map(ConstraintViolation::getMessage).
                        forEach(System.out::println);

            }
        }

    }

    private void categoryCreation() throws FileNotFoundException {
        CategorySeed[] dtos =
                this.gson.fromJson(new FileReader(CATEGORY_PATH), CategorySeed[].class);
        for (CategorySeed dto : dtos) {
            this.categoryService.createCategory(dto);
            System.out.println("Crated category: " + dto.getName());
        }
    }
}

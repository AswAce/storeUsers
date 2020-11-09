package example.JsonProject.services.views;

import com.google.gson.annotations.Expose;
import example.JsonProject.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsViewJson {
    @Expose
    private String name;
    @Expose
    private double price;
    @Expose
    private  String sellerFullName;
}

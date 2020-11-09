package example.JsonProject.services.views;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVIewBuyer {
    @Expose
    private String name;
    @Expose
    private double price;
    @Expose
    private String  buyerFirstName;
    private String  buyerLastName;
}

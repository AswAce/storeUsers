package example.JsonProject.services.seed;


import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSeed {

     @Expose
//     @Min(value = 3, message = "Last name should be longer")
    private String name;
     @Expose
     @DecimalMin("0")
     private double price;
}

package example.JsonProject.services.views;


import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserViewProducts {

    @Expose
    private String first_Name;
    @Expose
    private String last_Name;

    @Expose
    Set<ProductVIewBuyer> soldProducts;
}

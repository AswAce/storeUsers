package example.JsonProject.services.views;


import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserViewSecond {
    @Expose
    String firstName;
    @Expose
    String lastName;
    @Expose
    private Set<ProductVIewBuyer> soldProducts;
}

package example.JsonProject.services.seed;


import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSeedJSON {


    @Expose
    private String firstName;
    @Expose
//    @Min(value = 3, message = "Last name should be longer")
    private String lastName;
    @Expose
    private int age;
}

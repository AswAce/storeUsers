package example.JsonProject.services.views;


import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFullNameVIew {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
}

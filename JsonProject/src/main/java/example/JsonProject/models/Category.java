package example.JsonProject.models;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    private String name;

    @ManyToMany(mappedBy = "categories" , cascade = CascadeType.PERSIST)
    private Set<Product> products;
}

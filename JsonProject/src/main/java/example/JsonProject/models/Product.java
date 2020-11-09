package example.JsonProject.models;


import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    private double price;

    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST )
    @JoinTable(joinColumns =
    @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private Set<Category> categories;


    @ManyToOne(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    private User buyer;

    @ManyToOne(fetch = FetchType.EAGER)
    @NonNull
    @NotNull(message = " seller cannot be null")
//    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private User seller ;
}

package example.JsonProject.repositories;

import example.JsonProject.models.Product;

import example.JsonProject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    Set<Product> findByPriceGreaterThanAndBuyerNullAndPriceLessThanOrderByPriceAsc(double less, double more);

    @Query(value = "select u.id  from users as u\n" +
            "join products as p\n" +
            "on p.seller_id=u.id\n" +
            "where buyer_id is not Null " +
            "order by u.last_name asc, u.first_name asc;", nativeQuery = true)
    Set<Long> getUserWithBuyers();

    List<Product> findByBuyerEquals(long id);
}

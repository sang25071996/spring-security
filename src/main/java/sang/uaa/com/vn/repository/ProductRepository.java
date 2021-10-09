package sang.uaa.com.vn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sang.uaa.com.vn.product.entites.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query(value = "SELECT p.properties ->> 'name' FROM products.product p where p.properties ->> 'name'= :name", nativeQuery = true)
	String getProperties(@Param("name") String name);
	
	@Query("SELECT p.id FROM Product p WHERE LOWER(p.name) LIKE CONCAT('%', LOWER(:name), '%')")
	List<Long> findIdLikeName(@Param("name") String name);
}

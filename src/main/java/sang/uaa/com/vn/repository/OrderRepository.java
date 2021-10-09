package sang.uaa.com.vn.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sang.uaa.com.vn.product.entites.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query(value = "SELECT * FROM PRODUCTS.Order o WHERE o.name LIKE %:name% "
			+ "AND CASE  WHEN :productId <> '' THEN o.product_id IN (:productIds) ELSE CAST(o.product_id as TEXT) LIKE '%%'  END", nativeQuery = true)
	Page<Order> getPagination(@Param("name") String name, @Param("productId") String productId, @Param("productIds") List<Long> productIds, Pageable pageable);
}

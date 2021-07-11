package sang.uaa.com.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sang.uaa.com.vn.product.entites.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
}

package sang.uaa.com.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sang.uaa.com.vn.product.entites.ProductCategrories;

public interface ProductCategroriesRepository extends JpaRepository<ProductCategrories, Long> {
	
}

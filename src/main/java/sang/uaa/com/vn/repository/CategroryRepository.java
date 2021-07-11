package sang.uaa.com.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sang.uaa.com.vn.content.entites.Categrory;

@Repository
public interface CategroryRepository extends JpaRepository<Categrory, Long> {
	
}

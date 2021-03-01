package sang.uaa.com.vn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sang.uaa.com.vn.entites.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM User u WHERE u.username = :username")
	User findByUsername(@Param("username")String username);
	
	@Query("SELECT u FROM User u JOIN FETCH u.roles")
	List<User> getAllUsers();
}

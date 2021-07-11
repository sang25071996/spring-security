package sang.uaa.com.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sang.uaa.com.vn.user.entites.Privilege;
import sang.uaa.com.vn.user.entites.Role;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

	@Query("SELECT p FROM Privilege p WHERE p.id = :id")
	Role findByRoleId(@Param("id") Long id);
}

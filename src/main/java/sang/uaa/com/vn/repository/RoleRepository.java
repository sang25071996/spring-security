package sang.uaa.com.vn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sang.uaa.com.vn.entites.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	@Query("SELECT r FROM Role r WHERE r.id = :id")
	Role findByRoleId(@Param("id") Long id);
}

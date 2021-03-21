package sang.uaa.com.vn.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sang.uaa.com.vn.entites.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	@Query("SELECT r FROM Role r WHERE r.id = :id")
	Role findByRoleId(@Param("id") Long id);
	
	@Query("SELECT r FROM Role r WHERE (LOWER(r.name) LIKE %:name% OR UPPER(r.name) LIKE %:name%)")
	Page<Role> filterPaging(@Param("name") String name, Pageable pageable);
	
	@Query("SELECT r FROM Role r JOIN FETCH r.privileges")
	List<Role> getPrivileges();
}

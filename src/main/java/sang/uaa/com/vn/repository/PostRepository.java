package sang.uaa.com.vn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import sang.uaa.com.vn.content.entites.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	
	
	@Query("SELECT p FROM Post p JOIN FETCH p.categrory")
	List<Post> getPosts();
}

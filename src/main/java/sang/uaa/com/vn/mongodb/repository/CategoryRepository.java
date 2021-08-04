package sang.uaa.com.vn.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sang.uaa.com.vn.mongodb.Category;

import java.util.List;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

    @Query("{'name' : ?0}")
    List<Category> findCategorysByName(@Param("name") String name);

    @Query("{'name' : { $regex: ?0 }}")
    List<Category> findCategorysLikeName(@Param("name") String name);
}

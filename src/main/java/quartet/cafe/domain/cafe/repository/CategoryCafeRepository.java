package quartet.cafe.domain.cafe.repository;

import quartet.cafe.domain.category.model.CategoryCafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;
import quartet.cafe.domain.cafe.model.Cafe;

import java.util.List;

@Repository
public interface CategoryCafeRepository extends JpaRepository<CategoryCafe, Long> {

    @Query("SELECT cc.cafe FROM CategoryCafe cc WHERE cc.category.id = :categoryId")
    List<Cafe> findCafesByCategoryId(@Param("categoryId") Long categoryId);

    List<Cafe> findCafesByCategoryName(String category);
}


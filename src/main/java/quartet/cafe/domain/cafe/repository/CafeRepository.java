package quartet.cafe.domain.cafe.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import quartet.cafe.domain.cafe.model.Cafe;

@Repository
public interface CafeRepository extends JpaRepository<Cafe, Long> {
    @EntityGraph(attributePaths = {"reviews", "reviews.user"})
    Optional<Cafe> findWithReviewsById(Long id);
}
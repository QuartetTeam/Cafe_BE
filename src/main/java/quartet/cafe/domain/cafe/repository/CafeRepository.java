package quartet.cafe.domain.cafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quartet.cafe.domain.cafe.model.Cafe;

@Repository
public interface CafeRepository extends JpaRepository<Cafe, Long> {
}

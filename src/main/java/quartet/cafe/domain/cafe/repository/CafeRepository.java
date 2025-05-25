package quartet.cafe.domain.cafe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quartet.cafe.domain.cafe.model.Cafe;

public interface CafeRepository extends JpaRepository<Cafe, Long> {
}
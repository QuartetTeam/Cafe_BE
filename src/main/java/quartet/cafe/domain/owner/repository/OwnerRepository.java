package quartet.cafe.domain.owner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quartet.cafe.domain.owner.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}

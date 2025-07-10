package quartet.cafe.domain.ownercontent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quartet.cafe.domain.ownercontent.model.OwnerContent;

import java.util.List;

public interface OwnerContentRepository extends JpaRepository<OwnerContent, Long> {
    List<OwnerContent> findAllByOrderByCreatedAtDesc();
}
package quartet.cafe.domain.ownercontent.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import quartet.cafe.domain.ownercontent.model.OwnerContent;

import java.util.List;

public interface OwnerContentRepository extends JpaRepository<OwnerContent, Long> {
    // 모든 게시글을 최신순으로 조회. 삭제.
    // List<OwnerContent> findAllByOrderByCreatedAtDesc();

    // 특정 카페 게시글 최신순 + 페이지네이션
    Page<OwnerContent> findByCafeIdOrderByCreatedAtDesc(Long cafeId, Pageable pageable);
}
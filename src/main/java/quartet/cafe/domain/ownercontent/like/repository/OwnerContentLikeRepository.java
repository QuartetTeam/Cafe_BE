package quartet.cafe.domain.ownercontent.like.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quartet.cafe.domain.ownercontent.like.model.OwnerContentLike;

import java.util.Optional;

public interface OwnerContentLikeRepository extends JpaRepository<OwnerContentLike, Long> {

    //이미 좋아요 눌렀는지 확인
    Optional<OwnerContentLike> findByUserIdAndOwnerContentId(Long userId, Long ownerContentId);

    //게시글에 눌린 좋아요 개수
    Long countByOwnerContentId(Long ownerContentId);
}

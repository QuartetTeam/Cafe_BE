package quartet.cafe.domain.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quartet.cafe.domain.comment.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    //특정 게시글에 달린 댓글 목록 조회(최신순)
    List<Comment> findByOwnerContentIdOrderByCreatedAtDesc(Long ownerContentId);

    // 🔹 게시글 ID에 대한 댓글 수
    Long countByOwnerContentId(Long ownerContentId);
}
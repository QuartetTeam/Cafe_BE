package quartet.cafe.domain.comment.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quartet.cafe.domain.comment.dto.CommentRequest;
import quartet.cafe.domain.comment.dto.CommentResponse;
import quartet.cafe.domain.comment.model.Comment;
import quartet.cafe.domain.comment.repository.CommentRepository;
import quartet.cafe.domain.ownercontent.model.OwnerContent;
import quartet.cafe.domain.ownercontent.repository.OwnerContentRepository;
import quartet.cafe.domain.user.model.User;
import quartet.cafe.domain.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final OwnerContentRepository ownerContentRepository;

    @Transactional
    public CommentResponse createComment(CommentRequest request, Long userId) {
        //작성자 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        //게시글 조회
        OwnerContent ownerContent = ownerContentRepository.findById(request.getOwnerContentId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        //댓글 엔티티 생성
        Comment comment = new Comment(request.getContent(), user, ownerContent);

        //DB에 저장
        Comment savedComment = commentRepository.save(comment);

        //응답 DTO로 변환 후 반환
        return CommentResponse.from(savedComment);
    }

    @Transactional
    public List<CommentResponse> getCommentsByOwnerContentId(Long ownerContentId) {
        List<Comment> comments = commentRepository.findByOwnerContentIdOrderByCreatedAtDesc(ownerContentId);

        return comments.stream()
                .map(CommentResponse::from)
                .toList(); // Java 17 이상일 경우, Java 8이라면 .collect(Collectors.toList());
    }

    @Transactional
    public long countCommentsByOwnerContentId(Long ownerContentId) {
        return commentRepository.countByOwnerContentId(ownerContentId);
    }
}

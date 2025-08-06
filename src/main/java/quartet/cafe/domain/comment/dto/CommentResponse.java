package quartet.cafe.domain.comment.dto;

import lombok.Builder;
import lombok.Getter;
import quartet.cafe.domain.comment.model.Comment;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentResponse {

    private Long id;
    private String content;
    private LocalDateTime createdAt;

    private Long userId;
    private String userNickname;

    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .userId(comment.getUser().getId())
                .userNickname(comment.getUser().getName()) // 닉네임 필드가 없음.
                .build();
    }
}

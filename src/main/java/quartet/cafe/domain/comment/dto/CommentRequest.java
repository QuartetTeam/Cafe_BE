package quartet.cafe.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequest {

    @NotBlank(message = "댓글을 입력해주세요.")
    private String content;

    @NotNull(message = "게시글 ID는 필수입니다.")
    private Long ownerContentId;
}

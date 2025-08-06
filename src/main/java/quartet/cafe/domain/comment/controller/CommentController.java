package quartet.cafe.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import quartet.cafe.domain.comment.dto.CommentRequest;
import quartet.cafe.domain.comment.dto.CommentResponse;
import quartet.cafe.domain.comment.service.CommentService;
import quartet.cafe.infrastructure.security.CustomUserDetails;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 등록
    @PostMapping
    public ResponseEntity<CommentResponse> createComment(
            @RequestBody CommentRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long userId = userDetails.getUserId().longValue();
        CommentResponse response = commentService.createComment(request, userId);
        return ResponseEntity.ok(response);
    }

    // 댓글 목록 조회
    @GetMapping("/{ownerContentId}")
    public ResponseEntity<List<CommentResponse>> getCommentsByOwnerContentId(
            @PathVariable Long ownerContentId
    ) {
        List<CommentResponse> comments = commentService.getCommentsByOwnerContentId(ownerContentId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{ownerContentId}/count")
    public ResponseEntity<Long> countComments(
            @PathVariable Long ownerContentId
    ) {
        long count = commentService.countCommentsByOwnerContentId(ownerContentId);
        return ResponseEntity.ok(count);
    }
    //URL에서 {ownerContentId}를 PathVariable로 받음
    //CommentService의 메서드 호출
    //반환값 Long count을 그대로 응답으로 리턴
}

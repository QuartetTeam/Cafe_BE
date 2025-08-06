package quartet.cafe.domain.ownercontent.like.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import quartet.cafe.domain.ownercontent.like.service.OwnerContentLikeService;
import quartet.cafe.infrastructure.security.CustomUserDetails;

@RestController
@RequestMapping("/api/owner-contents/likes")
@RequiredArgsConstructor
public class OwnerContentLikeController {

    private final OwnerContentLikeService likeService;

    //하트 등록/해제 (토글)
    @PostMapping("/{ownerContentId}")
    public ResponseEntity<String> toggleLike(
            @PathVariable Long ownerContentId,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        Long userId = userDetails.getUserId().longValue(); // int → long
        boolean liked = likeService.toggleLike(userId, ownerContentId);

        String message = liked ? "하트 등록" : "하트 취소";
        return ResponseEntity.ok(message);
    }

    //하트 개수 조회
    @GetMapping("/{ownerContentId}/count")
    public ResponseEntity<Long> getLikeCount(@PathVariable Long ownerContentId) {
        long count = likeService.getLikeCount(ownerContentId);
        return ResponseEntity.ok(count);
    }
}


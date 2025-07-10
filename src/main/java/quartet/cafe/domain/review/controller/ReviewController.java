package quartet.cafe.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quartet.cafe.domain.review.dto.ReviewRequest;
import quartet.cafe.domain.review.dto.ReviewResponse;
import quartet.cafe.domain.review.service.ReviewService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping
    public ResponseEntity<ReviewResponse> createReview(@RequestBody ReviewRequest request) {
        ReviewResponse response = reviewService.createReview(request);
        return ResponseEntity.ok(response);
    }

    // 리뷰 수정
    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewResponse> updateReview(
            @PathVariable int reviewId,
            @RequestBody ReviewRequest request) {

        ReviewResponse response = reviewService.updateReview(reviewId, request);
        return ResponseEntity.ok(response);
    }

    // 평균 별점 조회
    @GetMapping("/average-score/{cafeId}")
    public ResponseEntity<BigDecimal> getAverageScore(@PathVariable Long cafeId) {
        BigDecimal averageScore = reviewService.getAverageScoreByCafeId(cafeId);
        return ResponseEntity.ok(averageScore);
    }

    // 특정 카페의 리뷰 목록 조회
    @GetMapping("/cafe/{cafeId}")
    public ResponseEntity<List<ReviewResponse>> getReviewsByCafe(@PathVariable Long cafeId) {
        List<ReviewResponse> reviews = reviewService.getReviewsByCafeId(cafeId);
        return ResponseEntity.ok(reviews);
    }

    // 리뷰 삭제 API
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable int reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok("리뷰가 삭제되었습니다.");
    }

}

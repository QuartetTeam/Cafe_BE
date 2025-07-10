package quartet.cafe.domain.review.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quartet.cafe.domain.review.dto.ReviewRequest;
import quartet.cafe.domain.review.dto.ReviewResponse;
import quartet.cafe.domain.review.model.Review;
import quartet.cafe.domain.review.repository.ReviewRepository;
import quartet.cafe.domain.user.model.User;
import quartet.cafe.domain.cafe.model.Cafe;
import quartet.cafe.domain.user.repository.UserRepository;
import quartet.cafe.domain.cafe.repository.CafeRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    // 필요한 Repository들을 주입
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final CafeRepository cafeRepository;

    // 리뷰 작성 기능
    @Transactional
    public ReviewResponse createReview(ReviewRequest request) {
        // 요청에서 받은 userId로 사용자 조회 (없으면 예외 발생)
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 요청에서 받은 cafeId로 카페 조회 (없으면 예외 발생)
        Cafe cafe = cafeRepository.findById(request.getCafeId())
                .orElseThrow(() -> new IllegalArgumentException("카페를 찾을 수 없습니다."));

        // Review 엔티티 생성
        Review review = Review.builder()
                .score(request.getScore())             // 별점
                .imageUrl(request.getImageUrl())       // 이미지 URL
                .content(request.getContent())         // 리뷰 내용
                .user(user)                            // 리뷰 작성자
                .cafe(cafe)                            // 리뷰 대상 카페
                .build();

        // 리뷰 DB에 저장
        Review saved = reviewRepository.save(review);

        // 저장된 리뷰 정보를 응답 DTO로 변환해 반환
        return ReviewResponse.builder()
                .id((long) saved.getId())
                .score(saved.getScore())
                .imageUrl(saved.getImageUrl())
                .content(saved.getContent())
                .userId((long) saved.getUser().getId())
                .cafeId((long) saved.getCafe().getId())
                .build();

    }

    // 특정 카페의 평균 별점 계산
    public BigDecimal getAverageScoreByCafeId(Long cafeId) {
        BigDecimal avg = reviewRepository.findAverageScoreByCafeId(cafeId);
        return avg != null ? avg : BigDecimal.ZERO; // 리뷰가 하나도 없으면 0 반환
    }

    // 특정 카페의 리뷰 목록 조회
    public List<ReviewResponse> getReviewsByCafeId(Long cafeId) {
        // DB에서 카페 ID에 해당하는 리뷰들 가져오기
        List<Review> reviews = reviewRepository.findByCafeId(cafeId);

        // 리뷰 리스트를 ReviewResponse 리스트로 변환해서 반환
        return reviews.stream()
            .map(review -> ReviewResponse.builder()
                    .id(review.getId())
                    .score(review.getScore())
                    .imageUrl(review.getImageUrl())
                    .content(review.getContent())
                    .userId(review.getUser().getId())
                    .cafeId(review.getCafe().getId())
                    .build())
            .collect(Collectors.toList());  // 타입을 List<ReviewResponse>로 명확히
}

    // 리뷰 수정 기능
    @Transactional
    public ReviewResponse updateReview(long reviewId, ReviewRequest request) {
        // 기존 리뷰 조회 (없으면 예외)
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다."));

        // 내용 수정
        review.update(
                request.getScore(),
                request.getImageUrl(),
                request.getContent()
        );

        // 수정된 리뷰를 DB에 저장
        Review updated = reviewRepository.save(review);

        // 응답 객체로 변환해서 반환
        return ReviewResponse.builder()
                .id((long) updated.getId())
                .score(updated.getScore())
                .imageUrl(updated.getImageUrl())
                .content(updated.getContent())
                .userId((long) updated.getUser().getId())
                .cafeId((long) updated.getCafe().getId())
                .build();

    }

    // 리뷰 삭제 메서드
    @Transactional
    public void deleteReview(long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다."));

        reviewRepository.delete(review);
    }

}

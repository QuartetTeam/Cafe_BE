package quartet.cafe.domain.cafe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quartet.cafe.domain.cafe.dto.CafeDetailResponse;
import quartet.cafe.domain.cafe.model.Cafe;
import quartet.cafe.domain.cafe.repository.CafeRepository;
import quartet.cafe.domain.review.dto.ReviewResponse;
import quartet.cafe.domain.review.model.Review;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CafeService {

    private final CafeRepository cafeRepository;

    public CafeDetailResponse getCafeDetail(Long cafeId) {
        // 카페 조회

        Cafe cafe = cafeRepository.findWithReviewsById(cafeId)

                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카페입니다."));

        // 리뷰 목록 가져오기
        List<Review> reviews = cafe.getReviews();

        // 리뷰 리스트 → ReviewResponse 리스트로 변환
        List<ReviewResponse> reviewResponses = reviews.stream()
                .map(r -> ReviewResponse.builder()
                        .id(r.getId())
                        .score(r.getScore())              // BigDecimal 타입 그대로 사용 가능
                        .imageUrl(r.getImageUrl())
                        .content(r.getContent())
                        .userId(r.getUser().getId())      // r.getUser()는 유저 객체에 따라 수정 필요
                        .cafeId(cafe.getId())
                        .build())
                .collect(Collectors.toList());

        // 평균 별점 계산 (소수 첫째 자리까지 반올림)
        float average = reviews.isEmpty()
                ? 0.0f
                : (float) (reviews.stream()
                    .mapToDouble(r -> r.getScore().floatValue())
                    .average()
                    .orElse(0.0));

        float rounded = Math.round(average * 10) / 10.0f;

        // 응답 DTO 생성
        return CafeDetailResponse.builder()
                .imageUrl(cafe.getImageUrl())
                .name(cafe.getName())
                .introduction(cafe.getIntroduction())
                .startAt(cafe.getStartAt())
                .endAt(cafe.getEndAt())
                .score(rounded)
                .reviews(reviewResponses)
                .build();
    }
}

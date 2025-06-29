package quartet.cafe.domain.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import quartet.cafe.domain.review.dto.ReviewResponse;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class CafeDetailResponse {
    private String imageUrl; // 카페 대표 이미지 URL
    private String name; // 카페 이름
    private String introduction; // 카페 소개 문구
    private LocalDateTime startAt; // 카페 영업 시작 시간
    private LocalDateTime endAt; // 카페 영업 종료 시간
    private float score; // 평균 별점 (리뷰 기반 계산)
    private List<ReviewResponse> reviews; // 리뷰 목록 (각 리뷰는 ReviewResponse DTO 형태로 제공됨)
}

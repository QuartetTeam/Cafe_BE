package quartet.cafe.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ReviewResponse {
    private final long id;
    private final BigDecimal score;
    private final String imageUrl;
    private final String content;
    private final long userId;
    private final long cafeId;

    @Builder
    public ReviewResponse(long id, BigDecimal score, String imageUrl, String content, long userId, long cafeId) {
        this.id = id;
        this.score = score;
        this.imageUrl = imageUrl;
        this.content = content;
        this.userId = userId;
        this.cafeId = cafeId;
    }
}

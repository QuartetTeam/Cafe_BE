package quartet.cafe.domain.review.dto;

import lombok.Getter;
import java.math.BigDecimal;

@Getter
public class ReviewRequest {
    private BigDecimal score;
    private String imageUrl;
    private String content;
    private Long userId;
    private Long cafeId;
}

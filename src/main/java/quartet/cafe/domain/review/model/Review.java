package quartet.cafe.domain.review.model;


import jakarta.persistence.*;
import lombok.*;
import quartet.cafe.domain.cafe.model.Cafe;
import quartet.cafe.common.BaseEntity;
import quartet.cafe.domain.user.model.User;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "review")
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_id")
    private long id;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal score;
    private String imageUrl;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cafe_id")
    private Cafe cafe;

    @Builder
    public Review(BigDecimal score, String imageUrl, String content, User user, Cafe cafe) {
        this.score = score;
        this.imageUrl = imageUrl;
        this.content = content;
        this.user = user;
        this.cafe = cafe;
    }

    // 수정용 메서드
    public void update(BigDecimal score, String imageUrl, String content) {
        this.score = score;
        this.imageUrl = imageUrl;
        this.content = content;
    }
}

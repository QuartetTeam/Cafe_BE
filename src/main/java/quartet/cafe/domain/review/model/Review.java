package quartet.cafe.review;


import jakarta.persistence.*;
import lombok.*;
import quartet.cafe.cafe.Cafe;
import quartet.cafe.common.BaseEntity;
import quartet.cafe.user.User;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "review")
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="review_id")
    private int id;

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


}

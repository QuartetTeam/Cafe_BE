package quartet.cafe.diary;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import quartet.cafe.cafe.Cafe;
import quartet.cafe.common.BaseEntity;
import quartet.cafe.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "diary")
public class Diary extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="diary_id")
    private int id;

    @Column(nullable = false, precision = 10, scale = 2) // DECIMAL(10,2)
    private BigDecimal score;

    private String imageUrl;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cafe_id")
    private Cafe cafe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Builder
    public Diary(BigDecimal score, String imageUrl, String content, LocalDateTime time, Cafe cafe, User user) {
        this.score = score;
        this.imageUrl = imageUrl;
        this.content = content;
        this.time = time;
        this.cafe = cafe;
        this.user = user;
    }
}

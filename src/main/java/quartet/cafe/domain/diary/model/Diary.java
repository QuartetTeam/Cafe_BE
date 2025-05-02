package quartet.cafe.diary;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import quartet.cafe.cafe.Cafe;
import quartet.cafe.common.BaseEntity;
import quartet.cafe.user.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "diary")
public class Diary extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="diary_id")
    private int id;

    private String imageUrl;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cafe_id")
    private Cafe cafe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Builder
    public Diary(String imageUrl, String content,Cafe cafe, User user) {
        this.imageUrl = imageUrl;
        this.content = content;
        this.cafe = cafe;
        this.user = user;
    }
}

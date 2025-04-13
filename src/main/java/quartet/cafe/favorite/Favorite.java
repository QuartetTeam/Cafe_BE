package quartet.cafe.favorite;

import jakarta.persistence.*;
import lombok.*;
import quartet.cafe.cafe.Cafe;
import quartet.cafe.common.BaseEntity;
import quartet.cafe.user.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "favorite")
public class Favorite extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="favorite_id")
    private int id;

    private boolean isFavorited;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cafe_id")
    private Cafe cafe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Builder
    public Favorite(boolean isFavorited, Cafe cafe, User user) {
        this.isFavorited = isFavorited;
        this.cafe = cafe;
        this.user = user;
    }
}

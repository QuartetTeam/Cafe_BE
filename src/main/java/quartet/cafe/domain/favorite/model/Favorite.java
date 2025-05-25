package quartet.cafe.domain.favorite.model;

import jakarta.persistence.*;
import lombok.*;
import quartet.cafe.domain.cafe.model.Cafe;
import quartet.cafe.common.BaseEntity;
import quartet.cafe.domain.user.model.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "favorite")
public class Favorite extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="favorite_id")
    private long id;

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

    public void toggle() {
        this.isFavorited = !this.isFavorited;
    }

}

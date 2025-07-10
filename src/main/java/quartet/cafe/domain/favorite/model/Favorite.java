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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cafe_id")
    private Cafe cafe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Builder
    public Favorite(Cafe cafe, User user) {
        this.cafe = cafe;
        this.user = user;
    }

}

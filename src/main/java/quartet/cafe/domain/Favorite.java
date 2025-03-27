package quartet.cafe.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import quartet.cafe.domain.base.BaseEntity;

@Entity
@Getter
@Setter
public class Favorite extends BaseEntity {
    @Id
    @GeneratedValue
    @Column (name="favorite_id")
    private int id;

    private boolean isFavorited;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cafe_id")
    private Cafe cafe;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
}

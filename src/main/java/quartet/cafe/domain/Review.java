package quartet.cafe.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import quartet.cafe.domain.base.BaseEntity;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Review extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name="review_id")
    private int id;

    @Column(nullable = false, precision = 10, scale = 2) // DECIMAL(10,2)
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

}

package quartet.cafe.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import quartet.cafe.domain.base.BaseEntity;

@Entity
@Getter
@Setter
public class OwnerContentReview extends BaseEntity {
    @Id
    @GeneratedValue
    @Column (name="owner_content_review_id")
    private int id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="owner_content_id")
    private OwnerContent ownerContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;


}

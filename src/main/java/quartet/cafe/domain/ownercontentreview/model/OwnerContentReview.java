package quartet.cafe.domain.ownercontentreview.model;

import jakarta.persistence.*;
import lombok.*;
import quartet.cafe.common.BaseEntity;
import quartet.cafe.domain.user.model.User;
import quartet.cafe.domain.ownercontent.model.OwnerContent;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "owner_content_review")
public class OwnerContentReview extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Builder
    public OwnerContentReview(String content, OwnerContent ownerContent, User user) {
        this.content = content;
        this.ownerContent = ownerContent;
        this.user = user;
    }
}

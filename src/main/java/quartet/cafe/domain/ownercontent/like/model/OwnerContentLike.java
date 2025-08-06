package quartet.cafe.domain.ownercontent.like.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import quartet.cafe.common.BaseEntity;
import quartet.cafe.domain.ownercontent.model.OwnerContent;
import quartet.cafe.domain.user.model.User;

@Entity
@Table(
        name = "owner_content_like",
        //한 사용자가 같은 게시글에 여러 번 좋아요 못 누르게 막아주는 조건
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "owner_content_id"})
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OwnerContentLike extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_content_like_id")
    private Long id; // 자동 증가

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_content_id", nullable = false)
    private OwnerContent ownerContent;

    public OwnerContentLike(User user, OwnerContent ownerContent) {
        this.user = user;
        this.ownerContent = ownerContent;
    }
}

package quartet.cafe.domain.comment.model;

// JPA 관련
import jakarta.persistence.*;

// Lombok: 생성자 및 getter 자동 생성
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 연관관계 대상
import quartet.cafe.domain.ownercontent.model.OwnerContent;
import quartet.cafe.domain.user.model.User;
// 공통 엔티티 상속 (createdAt, updatedAt 포함)
import quartet.cafe.common.BaseEntity;
import quartet.cafe.domain.ownercontent.model.OwnerContent;
import quartet.cafe.domain.user.model.User;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100) //댓글 글자수 제한. 100자
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_content_id", nullable = false)
    private OwnerContent ownerContent;

    // 생성자 - 댓글 등록 시 사용
    public Comment(String content, User user, OwnerContent ownerContent) {
        this.content = content;
        this.user = user;
        this.ownerContent = ownerContent;
    }
}
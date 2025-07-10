package quartet.cafe.domain.ownercontent.model;

import jakarta.persistence.*;
import lombok.*;
import quartet.cafe.domain.cafe.model.Cafe;
import quartet.cafe.common.BaseEntity;
import quartet.cafe.domain.ownercontntreview.model.OwnerContentReview;
import quartet.cafe.domain.owner.model.Owner;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "owner_content")
public class OwnerContent extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="owner_content_id")
    private long id;

    @Column(columnDefinition = "TEXT")
    private String introduction;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="owner_id")
    private Owner owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cafe_id")
    private Cafe cafe;

    @OneToMany (mappedBy = "ownerContent")
    private List<OwnerContentReview> ownerContentReviews=new ArrayList<>();

    @Builder
    public OwnerContent(String introduction, String imageUrl, Owner owner, Cafe cafe) {
        this.introduction = introduction;
        this.imageUrl = imageUrl;
        this.owner = owner;
        this.cafe = cafe;
    }
}


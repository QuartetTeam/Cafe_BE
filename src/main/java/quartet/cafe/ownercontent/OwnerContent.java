package quartet.cafe.ownercontent;

import jakarta.persistence.*;
import lombok.*;
import quartet.cafe.cafe.Cafe;
import quartet.cafe.common.BaseEntity;
import quartet.cafe.ownercontntreview.OwnerContentReview;
import quartet.cafe.owner.Owner;

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
    private int id;

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


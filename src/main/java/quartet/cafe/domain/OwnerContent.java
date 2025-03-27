package quartet.cafe.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import quartet.cafe.domain.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class OwnerContent extends BaseEntity {
    @Id
    @GeneratedValue
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

    @OneToMany (mappedBy = "owner")
    private List<OwnerContentReview> ownerContentReviews=new ArrayList<>();


}


package quartet.cafe.cafe;


import jakarta.persistence.*;
import lombok.*;
import quartet.cafe.category.CategoryCafe;
import quartet.cafe.diary.Diary;
import quartet.cafe.common.BaseEntity;
import quartet.cafe.favorite.Favorite;
import quartet.cafe.owner.Owner;
import quartet.cafe.ownercontent.OwnerContent;
import quartet.cafe.review.Review;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "cafe")
public class Cafe extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="cafe_id")
    private int id;

    private String name;

    private String imageUrl;

    @Column(columnDefinition = "TEXT")
    private String introduction;

    private String postcode;

    @Column(nullable = false, precision = 2, scale = 2) // 0.0 ~ 5.0 별점
    private BigDecimal score;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="owner_id")
    private Owner owner;

    @OneToMany(mappedBy = "cafe")
    private List<Review> reviews=new ArrayList<>();

    @OneToMany(mappedBy = "cafe")
    private List<OwnerContent> ownerContents=new ArrayList<>();

    @OneToMany(mappedBy = "cafe")
    private List<Diary> diaries =new ArrayList<>();

    @OneToMany(mappedBy = "cafe")
    private List<Favorite> favorites=new ArrayList<>();

    @OneToMany(mappedBy = "cafe")
    private List<CategoryCafe> categoryCafes= new ArrayList<>();

    @Builder
    public Cafe(String name, String imageUrl, String introduction, String postcode, BigDecimal score,
                LocalDateTime startAt, LocalDateTime endAt, Owner owner) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.introduction = introduction;
        this.postcode = postcode;
        this.score = score;
        this.startAt = startAt;
        this.endAt = endAt;
        this.owner = owner;
    }
}

package quartet.cafe.domain.user.model;


import jakarta.persistence.*;
import lombok.*;
import quartet.cafe.common.BaseEntity;
import quartet.cafe.domain.diary.model.Diary;
import quartet.cafe.domain.favorite.model.Favorite;
import quartet.cafe.domain.ownercontentreview.model.OwnerContentReview;
import quartet.cafe.domain.review.model.Review;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    private String kakaoId;

    private String name;

    private String password;

    private String email;

    private String phone;

    private String profileUrl;

    private String content;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews=new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<OwnerContentReview> ownerContentReviews=new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Diary> diaries =new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Favorite> favorites=new ArrayList<>();

    @Builder
    public User(String name, String kakaoId, String password, String email, String phone, String profileUrl, String content, SocialType socialType) {
        this.name = name;
        this.kakaoId = kakaoId;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.profileUrl = profileUrl;
        this.content = content;
        this.socialType = socialType;
    }

    public long getId() {
        return this.id;
    }

}

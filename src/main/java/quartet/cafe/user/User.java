package quartet.cafe.user;


import jakarta.persistence.*;
import lombok.*;
import quartet.cafe.common.BaseEntity;
import quartet.cafe.diary.Diary;
import quartet.cafe.favorite.Favorite;
import quartet.cafe.ownercontntreview.OwnerContentReview;
import quartet.cafe.review.Review;

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
    private int id;

    private String name;

    private String password;

    private String email;

    private String phone;

    private String profileUrl;

    private String content;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews=new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<OwnerContentReview> ownerContentReviews=new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Diary> diaries =new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Favorite> favorites=new ArrayList<>();

    @Builder
    public User(String name, String password, String email, String phone, String profileUrl, String content) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.profileUrl = profileUrl;
        this.content = content;
    }
}

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

public class User extends BaseEntity {

    @Id
    @GeneratedValue
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
    private List<Write> writes=new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Favorite> favorites=new ArrayList<>();
}

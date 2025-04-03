package quartet.cafe.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import quartet.cafe.domain.base.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Cafe extends BaseEntity {

    @Id
    @GeneratedValue
    @Column (name="cafe_id")
    private int id;
    private String name;
    private String imageUrl;

    @Column(columnDefinition = "TEXT")
    private String introduction;
    private String postcode;

    @Column(nullable = false, precision = 10, scale = 2) // DECIMAL(10,2)
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
    private List<Write> writes=new ArrayList<>();

    @OneToMany(mappedBy = "cafe")
    private List<Favorite> favorites=new ArrayList<>();

    @OneToMany(mappedBy = "cafe")
    private List<CategoryCafe> categoryCafes= new ArrayList<>();

}

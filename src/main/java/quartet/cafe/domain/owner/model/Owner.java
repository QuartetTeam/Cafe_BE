package quartet.cafe.domain.owner.model;

import jakarta.persistence.*;
import lombok.*;
import quartet.cafe.domain.cafe.model.Cafe;
import quartet.cafe.common.BaseEntity;
import quartet.cafe.domain.ownercontent.model.OwnerContent;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "owner")
public class Owner extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="owner_id")
    private int id;
    private String name;

    private String email;

    private String phone;

    private String password;

    @OneToMany (mappedBy = "owner")
    private List<Cafe> cafes=new ArrayList<>();

    @OneToMany (mappedBy = "owner")
    private List<OwnerContent> ownerContents=new ArrayList<>();

    @Builder
    public Owner(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
}

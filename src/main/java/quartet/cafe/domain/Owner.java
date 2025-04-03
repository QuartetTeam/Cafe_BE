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
public class Owner extends BaseEntity {
    @Id
    @GeneratedValue
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

}

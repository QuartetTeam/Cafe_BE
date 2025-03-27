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
public class Category extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name= "category_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "parent_id")
    private Category parent;
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }

    @OneToMany(mappedBy = "category")
    private List<CategoryCafe> categoryCafes= new ArrayList<>();
}

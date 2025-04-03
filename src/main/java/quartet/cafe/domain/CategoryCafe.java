package quartet.cafe.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import quartet.cafe.domain.base.BaseEntity;

@Entity
@Getter
@Setter
public class CategoryCafe extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name ="category_cafe_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;
}

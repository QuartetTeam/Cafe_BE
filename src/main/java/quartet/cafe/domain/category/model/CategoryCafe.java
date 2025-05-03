package quartet.cafe.domain.category.model;

import jakarta.persistence.*;
import lombok.*;
import quartet.cafe.domain.cafe.model.Cafe;
import quartet.cafe.common.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "category_cafe")
public class CategoryCafe extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="category_cafe_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

    public static CategoryCafe of(Category category, Cafe cafe) {
        CategoryCafe categoryCafe = new CategoryCafe();
        categoryCafe.category = category;
        categoryCafe.cafe = cafe;
        return categoryCafe;
    }
}

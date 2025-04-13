package quartet.cafe.category;

import jakarta.persistence.*;
import lombok.*;
import quartet.cafe.cafe.Cafe;
import quartet.cafe.common.BaseEntity;

@Entity
@Getter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "category_cafe")
public class CategoryCafe extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="category_cafe_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

}

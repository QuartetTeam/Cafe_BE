package quartet.cafe.domain.option.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import quartet.cafe.domain.cafe.model.Cafe;
import quartet.cafe.common.BaseEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "option_cafe")
public class OptionCafe extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_cafe_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id")
    private Option option;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

    public static OptionCafe of(Option option, Cafe cafe) {
        OptionCafe optionCafe = new OptionCafe();
        optionCafe.option = option;
        optionCafe.cafe = cafe;
        return optionCafe;
    }
}

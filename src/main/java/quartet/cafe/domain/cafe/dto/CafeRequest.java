package quartet.cafe.domain.cafe.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CafeRequest {
    private String name;
    private String imageUrl;
    private String introduction;
    private String postcode;
    private BigDecimal score;
}

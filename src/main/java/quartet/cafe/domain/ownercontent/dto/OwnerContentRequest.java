package quartet.cafe.domain.ownercontent.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerContentRequest {
    private String introduction;
    private String imageUrl;
    private Long cafeId;
    private Long ownerId;
}

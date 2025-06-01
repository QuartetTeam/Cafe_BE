package quartet.cafe.domain.ownercontent.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OwnerContentResponse {
    private Long id;
    private String introduction;
    private String imageUrl;
    private Long cafeId;
    private Long ownerId;

    @Builder
    public OwnerContentResponse(Long id, String introduction, String imageUrl, Long cafeId, Long ownerId) {
        this.id = id;
        this.introduction = introduction;
        this.imageUrl = imageUrl;
        this.cafeId = cafeId;
        this.ownerId = ownerId;
    }
}
package quartet.cafe.domain.favorite.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FavoriteResponse {
    private long id;
    private Long userId;
    private Long cafeId;
    private boolean isFavorited;

    @Builder
    public FavoriteResponse(long id, Long userId, Long cafeId, boolean isFavorited) {
        this.id = id;
        this.userId = userId;
        this.cafeId = cafeId;
        this.isFavorited = isFavorited;
    }
}

package quartet.cafe.domain.diary.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DiaryResponse {
    private Long id;
    private String imageUrl;
    private String content;
    private Long cafeId;
    private Long userId;

    @Builder
    public DiaryResponse(Long id, String imageUrl, String content, Long cafeId, Long userId) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.content = content;
        this.cafeId = cafeId;
        this.userId = userId;
    }
}

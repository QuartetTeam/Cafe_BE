package quartet.cafe.domain.diary.dto;

import lombok.Getter;

@Getter
public class DiaryRequest {
    private String imageUrl;
    private String content;
    private Long cafeId;
    private Long userId;
}

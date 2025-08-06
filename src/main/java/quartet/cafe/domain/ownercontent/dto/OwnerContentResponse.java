package quartet.cafe.domain.ownercontent.dto;

import lombok.Builder;
import lombok.Getter;
import quartet.cafe.domain.ownercontent.model.OwnerContent;

@Getter
public class OwnerContentResponse {
    private Long id;
    private String introduction;
    private String imageUrl;
    private Long cafeId;
    private Long ownerId;

    private long likeCount;
    private long commentCount;
    private long viewCount;

    @Builder
    public OwnerContentResponse(Long id,
                                String introduction,
                                String imageUrl,
                                Long cafeId,
                                Long ownerId,
                                long likeCount,
                                long commentCount,
                                long viewCount) {
        this.id = id;
        this.introduction = introduction;
        this.imageUrl = imageUrl;
        this.cafeId = cafeId;
        this.ownerId = ownerId;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.viewCount = viewCount;
    }

    public static OwnerContentResponse from(OwnerContent content, long likeCount, long commentCount) {
        return OwnerContentResponse.builder()
                .id(content.getId())
                .introduction(content.getIntroduction())
                .imageUrl(content.getImageUrl())
                .cafeId(content.getCafe().getId())
                .ownerId(content.getOwner().getId())
                .likeCount(likeCount)
                .commentCount(commentCount)
                .viewCount(content.getViewCount())  //엔티티에서 가져옴
                .build();
    }
}
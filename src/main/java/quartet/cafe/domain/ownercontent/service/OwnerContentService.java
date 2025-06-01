package quartet.cafe.domain.ownercontent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quartet.cafe.domain.ownercontent.dto.OwnerContentResponse;
import quartet.cafe.domain.ownercontent.model.OwnerContent;
import quartet.cafe.domain.ownercontent.repository.OwnerContentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerContentService {

    private final OwnerContentRepository ownerContentRepository;

    // 전체 조회
    public List<OwnerContentResponse> getAllOwnerContents() {
        return ownerContentRepository.findAll().stream()
                .map(content -> OwnerContentResponse.builder()
                        .id(content.getId())
                        .introduction(content.getIntroduction())
                        .imageUrl(content.getImageUrl())
                        .cafeId(content.getCafe().getId())
                        .ownerId(content.getOwner().getId())
                        .build()
                ).collect(Collectors.toList());
    }

    // 상세 조회
    public OwnerContentResponse getOwnerContentById(Long id) {
        OwnerContent content = ownerContentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));
        return OwnerContentResponse.builder()
                .id(content.getId())
                .introduction(content.getIntroduction())
                .imageUrl(content.getImageUrl())
                .cafeId(content.getCafe().getId())
                .ownerId(content.getOwner().getId())
                .build();
    }
}

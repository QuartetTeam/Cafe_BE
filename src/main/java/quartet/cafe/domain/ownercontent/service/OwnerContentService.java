package quartet.cafe.domain.ownercontent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quartet.cafe.domain.cafe.model.Cafe;
import quartet.cafe.domain.cafe.repository.CafeRepository;
import quartet.cafe.domain.comment.repository.CommentRepository;
import quartet.cafe.domain.owner.model.Owner;
import quartet.cafe.domain.owner.repository.OwnerRepository;
import quartet.cafe.domain.ownercontent.dto.OwnerContentRequest;
import quartet.cafe.domain.ownercontent.dto.OwnerContentResponse;
import quartet.cafe.domain.ownercontent.like.repository.OwnerContentLikeRepository;
import quartet.cafe.domain.ownercontent.model.OwnerContent;
import quartet.cafe.domain.ownercontent.repository.OwnerContentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerContentService {

    private final OwnerContentRepository ownerContentRepository;
    private final OwnerRepository ownerRepository;      // 사장님 게시글 등록용
    private final CafeRepository cafeRepository;        // 사장님 게시글 등록용
    private final OwnerContentLikeRepository likeRepository;
    private final CommentRepository commentRepository;

    // 특정 카페의 게시글 목록 조회 (최신순으로 6개씩 페이지네이션)
    public List<OwnerContentResponse> getOwnerContentsByCafeId(Long cafeId, Pageable pageable) {
        Page<OwnerContent> contents = ownerContentRepository.findByCafeIdOrderByCreatedAtDesc(cafeId, pageable);
        return contents.stream()
                .map(content -> OwnerContentResponse.builder()
                        .id(content.getId())
                        .introduction(content.getIntroduction())
                        .imageUrl(content.getImageUrl())
                        .cafeId(content.getCafe().getId())
                        .ownerId(content.getOwner().getId())
                        .build())
                .collect(Collectors.toList());
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

    // 게시글 등록
    @Transactional
    public OwnerContentResponse createOwnerContent(OwnerContentRequest request) {
        Owner owner = ownerRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("해당 사장님이 존재하지 않습니다."));
        Cafe cafe = cafeRepository.findById(request.getCafeId())
                .orElseThrow(() -> new IllegalArgumentException("해당 카페가 존재하지 않습니다."));

        OwnerContent content = OwnerContent.builder()
                .introduction(request.getIntroduction())
                .imageUrl(request.getImageUrl())
                .owner(owner)
                .cafe(cafe)
                .build();

        OwnerContent saved = ownerContentRepository.save(content);

        return OwnerContentResponse.builder()
                .id(saved.getId())
                .introduction(saved.getIntroduction())
                .imageUrl(saved.getImageUrl())
                .ownerId(saved.getOwner().getId())
                .cafeId(saved.getCafe().getId())
                .build();
    }

    public OwnerContentResponse getOwnerContentDetail(Long contentId) {
        // 1. 게시글 조회
        OwnerContent content = ownerContentRepository.findById(contentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 존재하지 않습니다."));

        // 2. 좋아요 수 조회
        long likeCount = likeRepository.countByOwnerContentId(contentId);

        // 3. 댓글 수 조회
        long commentCount = commentRepository.countByOwnerContentId(contentId);

        // 4. 응답 DTO 생성 (viewCount는 엔티티 필드에서 가져옴)
        return OwnerContentResponse.from(content, likeCount, commentCount);
    }


}